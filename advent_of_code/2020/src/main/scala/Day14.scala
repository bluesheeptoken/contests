object Day14 extends Day[Long, Long] {
  sealed trait Instruction
  final case class Bitmask(mask: String) extends Instruction {
    val andMask: Long = java.lang.Long.parseLong(mask.replace('X', '1'), 2)
    val orMask: Long  = java.lang.Long.parseLong(mask.replace('X', '0'), 2)
    def computePart1(memory: Memory): Map[Long, Long] =
      Map(memory.address -> (memory.value & andMask | orMask))

    def computePart2(memory: Memory): Map[Long, Long] = {
      val bin           = memory.address.toBinaryString
      val binaryAddress = "0" * (36 - bin.length) + bin
      val maskedAddress = binaryAddress
        .zip(mask).map { case (addElt, maskElt) =>
          if (maskElt == '0') addElt else maskElt
        }.mkString("")

      val replacements = {
        def f(index: Int, maxSize: Int, acc: List[Int]): List[List[Int]] =
          if (index == maxSize)
            List(acc)
          else {
            val x = f(index + 1, maxSize, acc)
            x.map(1 :: _) ++ x.map(0 :: _)
          }
        f(0, mask.count(_ == 'X'), Nil)
      }

      replacements.map { r =>
        val x = r
          .foldLeft(maskedAddress) { case (acc, elt) => acc.replaceFirst("X", elt.toString) }
        java.lang.Long.parseLong(x, 2) -> memory.value
      }.toMap
    }
  }
  object Bitmask {
    val noEffectBitmask: Bitmask = Bitmask("X" * 36)
  }
  final case class Memory(address: Long, value: Long) extends Instruction

  object Instruction {
    def parse(s: String): Option[Instruction] = s match {
      case s"mask = $value"      => Some(Bitmask(value))
      case s"mem[$add] = $value" => Some(Memory(add.toInt, value.toInt))
      case _                     => None
    }
  }

  val instructions: List[Instruction] =
    lines.toList.map(Instruction.parse(_).getOrElse(throw ParsingException()))

  def doRun(f: (Bitmask, Memory) => Map[Long, Long]): Long =
    instructions
      .foldLeft(Bitmask.noEffectBitmask, Map.empty[Long, Long]) {
        case ((currentMask, values), instruction) =>
          instruction match {
            case mask: Bitmask => (mask, values)
            case memory: Memory =>
              (currentMask, values ++ f(currentMask, memory))
          }
      }._2.values.sum

  override def part1: Long =
    doRun(_ computePart1 _)

  override def part2: Long =
    doRun(_ computePart2 _)
}
