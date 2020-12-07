import Instruction._

import scala.collection.immutable.BitSet

class Computer(var instructions: Vector[Instruction]) {
  private var acc      = 0
  private var i        = 0
  private var executed = BitSet(instructions.size)

  def execute(): Unit =
    while (!executed(i) && i < instructions.size) {
      executed += i
      instructions(i) match {
        case Acc(value)  => acc += value; i += 1
        case Jmp(offset) => i += offset
        case Nop(_)      => i += 1
      }
    }

  def reinitialize(instructions: Vector[Instruction]): Unit = {
    this.instructions = instructions
    acc = 0
    i = 0
    executed = BitSet(instructions.size)
  }

  def hasSuccessfullyFinished: Boolean = i == instructions.size

  def value: Int = acc
}

sealed trait Instruction

object Instruction {
  final case class Acc(acc: Int)    extends Instruction
  final case class Jmp(offset: Int) extends Instruction
  final case class Nop(value: Int)  extends Instruction

  def parse(s: String): Option[Instruction] = s match {
    case s"nop $int" => Some(Nop(int.toInt))
    case s"acc $int" => Some(Acc(int.toInt))
    case s"jmp $int" => Some(Jmp(int.toInt))
    case _           => None
  }
}
