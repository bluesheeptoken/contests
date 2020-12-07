import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day7Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "count number of distinct colors that can contain shiny gold bag" in {
      Day7.part1 shouldBe 254
    }
  }

  "part2" should {
    "compute number of bags that a shiny gold bag can contain" in {
      Day7.part2 shouldBe 6006
    }
  }
}
