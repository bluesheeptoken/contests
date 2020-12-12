object Day10 extends Day[Int, Long] {

  val numbers: List[Int] = 0 :: lines.toList.map(_.toInt).sorted

  override def part1: Int = {
    val m: Map[Int, Int] =
      numbers.zip(numbers.drop(1)).map(x => x._2 - x._1).groupMapReduce(identity)(_ => 1)(_ + _)
    m.getOrElse(1, 0) * (m.getOrElse(3, 0) + 1)
  }

  override def part2: Long = {
    val ans = Array.fill(numbers.size)(0L)
    ans(ans.length - 1) = 1
    for (i <- (0 until ans.length - 1).reverse) {
      var tmp = 0L
      for (j <- (i + 1 until i + 4))
        if (j < ans.length && numbers(j) - numbers(i) <= 3)
          tmp += ans(j)
      ans(i) = tmp
    }
    ans(0)
  }
}
