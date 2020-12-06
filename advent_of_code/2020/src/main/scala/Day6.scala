import scala.collection.immutable.BitSet
import scala.collection.mutable.ListBuffer

object Day6 extends Day[Int, Int] {
  type GroupAnswers = List[String]

  override def part1: Int = reduce(_ | _)

  override def part2: Int = reduce(_ & _)

  private def reduce(op: (BitSet, BitSet) => BitSet): Int =
    groupAnswers.map(_.reduce(op).count(_ => true)).sum

  val groupAnswers: List[List[BitSet]] = split(lines.toList, "").map { answers =>
    answers.map(answer => BitSet.fromSpecific(answer.map(l => l.toInt - 86)))
  }

  private def split(lines: List[String], sep: String): List[List[String]] = {
    val parent = new ListBuffer[List[String]]
    val child  = new ListBuffer[String]
    lines.foreach { line =>
      if (line != sep) {
        val _ = child.addOne(line)
      } else {
        val _ = parent.addOne(child.toList)
        child.clear()
      }
    }
    val _ = parent.addOne(child.toList)
    parent.toList
  }
}
