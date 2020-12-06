import scala.io.Codec.defaultCharsetCodec

trait Day[O1, O2] {
  def part1: O1
  def part2: O2
  def run(): Unit = {
    println(s"Day ${day()}, part1: $part1")
    println(s"Day ${day()}, part2: $part2")
  }

  def lines: Iterator[String] = {
    val is = this.getClass.getClassLoader.getResourceAsStream(filename)
    io.Source.fromInputStream(is)(defaultCharsetCodec).getLines()
  }

  def day(): String = {
    val clazz = this.getClass.getSimpleName
    clazz.substring(3, clazz.length - 1)
  }

  private def filename =
    s"input${day()}"
}
