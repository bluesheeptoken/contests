import Instruction._

object Day8 extends Day[Int, Int] {

  val instructions: Vector[Instruction] =
    lines.toVector.map(Instruction.parse(_).getOrElse(throw ParsingException()))

  override def part1: Int = {
    val computer = new Computer(instructions)
    computer.execute()
    computer.value
  }

  override def part2: Int =
    instructions.zipWithIndex.map { case (elt, i) =>
      elt match {
        case Nop(value) =>
          val comp = new Computer(
            instructions.take(i) ++ Vector(Jmp(value)) ++ instructions.takeRight(
              instructions.size - i - 1
            )
          )
          comp.execute()
          if (comp.hasSuccessfullyFinished) comp.value else -1
        case Jmp(offset) =>
          val comp = new Computer(
            instructions.take(i) ++ Vector(Nop(offset)) ++ instructions.takeRight(
              instructions.size - i - 1
            )
          )
          comp.execute()
          if (comp.hasSuccessfullyFinished) comp.value else -1
        case Acc(_) => -1
      }
    }.max
}
