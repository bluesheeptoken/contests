object Day17 extends Day[Int, Int] {
  override def part1: Int = {
    val grid = Grid(initialGrid.map { case (x, y) => Point3D(x, y, 0) })
    Iterator.iterate(grid)(next).drop(6).next().actives.size
  }
  override def part2: Int = {
    val grid = Grid(initialGrid.map { case (x, y) => Point4D(x, y, 0, 0) })
    Iterator.iterate(grid)(next).drop(6).next().actives.size
  }

  val initialGrid =
    lines.toList.zipWithIndex
      .collect { case (line, y) =>
        line.toList.zipWithIndex.collect { case (c, x) if c == '#' => (x, y) }
      }.flatten.toSet

  final case class Grid[P <: Point](actives: Set[Point]) {
    def activeNeighbors(p: Point): Int =
      actives.intersect(p.neighbours).size

    def isActive(p: Point): Boolean = actives.contains(p)
  }
  final case class Point3D(x: Int, y: Int, z: Int) extends Point {
    override def neighbours: Set[Point] = (for {
      dx <- x - 1 to x + 1
      dy <- y - 1 to y + 1
      dz <- z - 1 to z + 1
      if !(dx == x && dy == y && dz == z)
    } yield Point3D(dx, dy, dz)).toSet
  }

  final case class Point4D(w: Int, x: Int, y: Int, z: Int) extends Point {
    override def neighbours: Set[Point] = (for {
      dw <- w - 1 to w + 1
      dx <- x - 1 to x + 1
      dy <- y - 1 to y + 1
      dz <- z - 1 to z + 1
      if !(dx == x && dy == y && dz == z && dw == w)
    } yield Point4D(dw, dx, dy, dz)).toSet
  }

  private def next[P <: Point](g: Grid[P]): Grid[P] = {
    val actives = for {
      p   <- g.actives
      ngh <- p.neighbours // TODO: smart neighbours to avoid iterating several times on same points
      count = g.activeNeighbors(ngh)
      if (g.isActive(ngh) && (count == 2 || count == 3)) || (!g.isActive(ngh) && count == 3)
    } yield ngh
    Grid[P](actives)
  }

  trait Point {
    def neighbours: Set[Point]
  }
}
