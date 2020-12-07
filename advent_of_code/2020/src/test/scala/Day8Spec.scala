import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day8Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "compute the value and exits before entering in a inifinite loop" in {
      Day8.part1 shouldBe 1753
    }
  }

  "part2" should {
    "fix the corrupted boot program and compute the value" in {
      Day8.part2 shouldBe 733
    }
  }
}
