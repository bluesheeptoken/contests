import scala.collection.mutable

object Day15 extends Day[Int, Int] {
  val numbers: Seq[Int] = lines.toList.head.split(',').map(_.toInt)

  def doRun(n: Int): Int = {
    // Mutable for performances
    val map     = mutable.Map.empty[Int, Int]
    val history = mutable.Set.empty[Int]

    (0 until n)
      .foldLeft(-1) { case (res, idx) =>
        if (idx < numbers.size) {
          map(numbers(idx)) = idx
          history += res
          numbers(idx)
        } else {
          val newRes = if (!history.contains(res)) 0 else idx - map(res) - 1
          map(res) = idx - 1
          history += res
          newRes
        }

      }
  }

  override def part1: Int = // 758
    doRun(2020)

  override def part2: Int = // 814
    doRun(30000000)

}
