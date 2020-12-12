object Day12 extends Day[Int, Int] {
  override def part1: Int = {
    val finalPosition = lines.foldLeft(Ship(Orientation.east, Position(0, 0))) { (ship, line) =>
      val value = line.tail.toInt
      line.head match {
        case 'N' => ship.copy(position = ship.position + (Orientation.north * value))
        case 'E' => ship.copy(position = ship.position + (Orientation.east * value))
        case 'S' => ship.copy(position = ship.position + (Orientation.south * value))
        case 'W' => ship.copy(position = ship.position + (Orientation.west * value))
        case 'R' => ship.copy(orientation = ship.orientation.rotate(value))
        case 'L' => ship.copy(orientation = ship.orientation.rotate(360 - value))
        case 'F' => ship.copy(position = ship.position + (ship.orientation * value))
      }
    }
    (math.abs(finalPosition.position.x) + math.abs(finalPosition.position.y)).toInt
  }

  override def part2: Int = {
    val end = lines.foldLeft(Ship(Orientation(10, -1), Position(0, 0))) { (ship, line) =>
      val value = line.tail.toInt
      line.head match {
        case 'N' => ship.copy(orientation = ship.orientation + (Orientation.north * value))
        case 'E' => ship.copy(orientation = ship.orientation + (Orientation.east * value))
        case 'S' => ship.copy(orientation = ship.orientation + (Orientation.south * value))
        case 'W' => ship.copy(orientation = ship.orientation + (Orientation.west * value))
        case 'R' => ship.copy(orientation = ship.orientation.rotate(value))
        case 'L' => ship.copy(orientation = ship.orientation.rotate(360 - value))
        case 'F' => ship.copy(position = ship.position + (ship.orientation * value))
      }
    }
    (math.abs(end.position.x) + math.abs(end.position.y)).toInt
  }

  final case class Orientation(dx: Double, dy: Double) {
    def *(n: Int): Orientation = Orientation(n * dx, n * dy)
    def +(orientation: Orientation): Orientation =
      Orientation(orientation.dx + dx, orientation.dy + dy)
    def rotate(alpha: Int): Orientation = { // angle in degrees
      val angle = math.toRadians(alpha.toDouble)
      Orientation(
        dx * math.cos(angle).toInt - dy * math.sin(angle).toInt,
        dx * math.sin(angle).toInt + dy * math.cos(angle).toInt
      ) // magically converting toInt after each trio function works
    }
  }

  object Orientation {
    val north: Orientation = Orientation(0, -1)
    val east: Orientation  = Orientation(1, 0)
    val south: Orientation = Orientation(0, 1)
    val west: Orientation  = Orientation(-1, 0)
    def parse(s: Char): Option[Orientation] = s match {
      case 'N' => Some(north)
      case 'E' => Some(east)
      case 'S' => Some(south)
      case 'W' => Some(west)
      case _   => None
    }
  }

  final case class Position(x: Double, y: Double) {
    def +(orientation: Orientation): Position = Position(x + orientation.dx, y + orientation.dy)
  }

  final case class Ship(orientation: Orientation, position: Position)
}
