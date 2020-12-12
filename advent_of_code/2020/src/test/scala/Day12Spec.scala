import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day12Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "compute manathan distance" in {
      Day12.part1 shouldBe 1710
    }
  }

  "part2" should {
    "count occupied seats" in {
      Day12.part2 shouldBe 62045
    }
  }
}
