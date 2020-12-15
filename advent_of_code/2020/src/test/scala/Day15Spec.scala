import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day15Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "compute the 2020th number" in {
      Day15.part1 shouldBe 758
    }
  }

  "part2" should {
    "compute the 30000000th" in {
      Day15.part2 shouldBe 814
    }
  }
}
