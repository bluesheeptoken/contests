import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day13Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "compute the waiting time multiplied by the bus id of the next departure" in {
      Day13.part1 shouldBe 2845
    }
  }

  "part2" should {
    "solve the Chinese Remained Theorem" in {
      Day13.part2 shouldBe 487905974205117L
    }
  }
}
