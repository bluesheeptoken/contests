object Day3 extends Day[Int, Long] {
  private val tree = '#'

  override def part1: Int = encounter(1, 1, grid)

  override def part2: Long =
    List((1, 1), (3, 1), (5, 1), (7, 1), (1, 2)).foldLeft(1L) { case (acc, (stepX, stepY)) =>
      acc * encounter(stepX, stepY, grid)
    }

  def encounter(stepX: Int, stepY: Int, grid: List[List[Char]]): Int = {
    val height = grid.size // TODO: do not use lists
    val width  = grid.head.size
    LazyList.from(0, stepX).zip(0 until height by stepY).count { case (x, y) =>
      grid(y)(x % width) == tree
    }
  }

  private val grid: List[List[Char]] = lines.map(_.toList).toList
}
