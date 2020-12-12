object Day9 extends Day[Long, Long] {

  val numbers: Vector[Long] = lines.toVector.map(_.toLong)

  def run(x: Int, number: Vector[Long]): Option[Long] =
    number
      .sliding(x + 1)
      .map(g => (g.last, g.dropRight(1).combinations(2).map(_.sum)))
      .find { case (n, group) => !group.contains(n) }
      .map(_._1)

  override def part1: Long = run(26, numbers).getOrElse(throw SolutionNotFoundException())

  override def part2: Long = {
    val target = part1
    var i      = 0
    var j      = 1
    var sum    = numbers.head
    while (sum != target)
      if (sum < target) {
        sum += numbers(j)
        j += 1
      } else {
        sum -= numbers(i)
        i += 1
      }
    val x = numbers.slice(i, j)
    x.max + x.min
  }
}
