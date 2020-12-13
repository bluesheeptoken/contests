object Day13 extends Day[Int, Long] {
  override def part1: Int = {
    val ts         = listLines.head.toInt
    val ids        = listLines(1).split(',').flatMap(_.toIntOption)
    val departures = ids.map(id => ((math.ceil(ts.toDouble / id) * id).toInt, id))
    val (d, id)    = departures.minBy(_._1)
    (d - ts) * id

  }

  override def part2: Long =
    listLines(1)
      .split(',')
      .zipWithIndex
      .filter(_._1 != "x")
      .map(x => (x._1.toInt, x._2))
      .sortBy(-_._1)
      .foldLeft((0L, 1L)) { case ((idx, step), (bus, delay)) =>
        (
          Iterator
            .iterate(idx)(x => x + step)
            .find(x => (x + delay) % bus == 0)
            .get,
          step * bus
        )
      }._1

  val listLines = lines.toList
}
