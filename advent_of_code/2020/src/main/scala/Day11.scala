object Day11 extends Day[Int, Int] {
  private def run(grid: Grid, neighbors: Position => Seq[Position], maxNumberOfNghbs: Int): Int =
    Iterator
      .iterate(grid)(applyRules(neighbors, maxNumberOfNghbs))
      .sliding(2)
      .find(seq => seq.head == seq(1))
      .getOrElse(throw SolutionNotFoundException())
      .head
      .count(_ == Occupied)

  private def applyRules(neighbors: Position => Seq[Position], limit: Int)(grid: Grid): Grid = {
    val newGrid = grid.fill(Other)
    newGrid.positions.foreach { pos =>
      grid(pos) match {
        case Floor                                                        => newGrid(pos) = Floor
        case Empty if neighbors(pos).count(p => grid(p) == Occupied) == 0 => newGrid(pos) = Occupied
        case Occupied if neighbors(pos).count(p => grid(p) == Occupied) >= limit =>
          newGrid(pos) = Empty
        case _ => newGrid(pos) = grid(pos)
      }
    }
    newGrid
  }

  override def part1: Int =
    run(grid, pos => grid.neighbours(pos), 4)

  override def part2: Int =
    run(grid, pos => Direction.all.flatMap(dir => nextVisible(grid, pos, dir)), 5)

  val grid: Grid =
    Grid(
      lines.toArray
        .map(_.toArray.map(Element.parse(_).getOrElse(throw ParsingException())))
    )

  private def nextVisible(grid: Grid, start: Position, dir: (Int, Int)): Option[Position] =
    Iterator
      .unfold(start)(p => grid.move(p, dir).map(pos => (pos, pos)))
      .find(p => grid(p) != Floor)

  sealed trait Element

  case object Floor    extends Element
  case object Empty    extends Element
  case object Occupied extends Element
  case object Other    extends Element

  object Element {
    def parse(c: Char): Option[Element] = c match {
      case '.' => Some(Floor)
      case 'L' => Some(Empty)
      case '#' => Some(Occupied)
      case _   => None
    }
  }

  object Direction {
    val all = Seq((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
  }
  case class Position(x: Int, y: Int)

  case class Grid(arr: Array[Array[Element]]) {
    private val rowCount = arr.length
    private val colCount = arr.head.length

    def apply(p: Position): Element           = arr(p.y)(p.x)
    def update(p: Position, c: Element): Unit = arr(p.y)(p.x) = c

    def count(p: Element => Boolean): Int = arr.map(_.count(p)).sum

    def fill(elem: Element): Grid = Grid(Array.fill(rowCount)(Array.fill(colCount)(elem)))

    def move(p: Position, dir: (Int, Int)): Option[Position] = {
      val newp = Position(p.x + dir._1, p.y + dir._2)
      if (newp.x >= 0 && newp.y >= 0 && newp.x < colCount && newp.y < rowCount) Some(newp) else None
    }

    def positions: Seq[Position] =
      for {
        i <- 0 until colCount
        j <- 0 until rowCount
      } yield Position(i, j)

    def neighbours(p: Position): Seq[Position] =
      Direction.all
        .map(d => Position(p.x + d._1, p.y + d._2))
        .filter(p => p.x >= 0 && p.y >= 0 && p.x < colCount && p.y < rowCount)

    override def equals(other: Any): Boolean =
      other match {
        case other: Grid => positions.forall(p => apply(p) == other(p))
        case _           => false
      }
  }
}
