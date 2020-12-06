import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day6Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "computes sum of distinct answers per group from input" in {
      Day6.part1 shouldBe 6809
    }
  }

  "part2" should {
    "computes sum of common answers per group from input" in {
      Day6.part2 shouldBe 3394
    }
  }
}
