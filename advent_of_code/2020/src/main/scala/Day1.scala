object Day1 extends Day[Int, Int] {
  override def part1: Int = twoSum(numbers, 2020) match {
    case Some((a, b)) => a * b
    case None         => throw SolutionNotFoundException()
  }

  override def part2: Int =
    numbers
      .flatMap(x => twoSum(numbers, 2020 - x).map { case (a, b) => (x, a, b) })
      .headOption match {
      case Some((a, b, c)) => a * b * c
      case None            => throw SolutionNotFoundException()
    }

  private val numbers = lines.map(_.toInt).toSet

  def twoSum(numbers: Set[Int], target: Int): Option[(Int, Int)] =
    numbers.find(n => numbers.contains(target - n)).map(n => (n, target - n))
}
