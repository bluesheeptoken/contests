import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day17Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "count active points after 6 cycles in a 3D space" in {
      Day17.part1 shouldBe 242
    }
  }

  "part2" should {
    "count active points after 6 cycles in a 4D space" in {
      Day17.part2 shouldBe 2292
    }
  }
}
