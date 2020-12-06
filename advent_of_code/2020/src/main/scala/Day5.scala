import scala.collection.immutable.BitSet

object Day5 extends Day[Int, Int] {
  def seatId(cmds: List[Char]): Int =
    cmds.foldLeft(0) { case (id, cmd) =>
      (id << 1) + (if (cmd == 'B' || cmd == 'R') 1 else 0)
    }

  private val seats = lines.map(l => seatId(l.toList)).toList

  override def part1: Int =
    seats.max

  override def part2: Int = {
    val b = BitSet.fromSpecific(seats)
    (1 until 128 * 8 + 7)
      .find(i => !b(i) && b(i - 1) && b(i + 1))
      .getOrElse(throw new SolutionNotFoundException())
  }
}
