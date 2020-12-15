import scala.io.Codec.defaultCharsetCodec

trait Day[O1, O2] {
  def part1: O1
  def part2: O2
  def run(): Unit = {
    val (p1, t1) = withTimer(part1)
    println(s"Day ${day()}, part1: $p1, time: ${t1}ms")
    val (p2, t2) = withTimer(part2)
    println(s"Day ${day()}, part2: $p2, time: ${t2}ms")
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

  def withTimer[A](a: => A): (A, Long) = {
    val s   = System.nanoTime()
    val ret = a
    val e   = System.nanoTime()
    (ret, (e - s) / (1000 * 1000))
  }
}
