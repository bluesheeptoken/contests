import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day11Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "count occupied seats" in {
      Day11.part1 shouldBe 2126
    }
  }

  "part2" should {
    "count occupied seats" in {
      Day11.part2 shouldBe 1914
    }
  }
}
