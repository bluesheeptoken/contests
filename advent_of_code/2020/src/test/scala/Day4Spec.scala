import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day4Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "count number of passports with all the mandatory fields form inputs" in {
      Day4.part1 shouldBe 213
    }
  }

  "part2" should {
    "count number of passports with valid fields from input" in {
      Day4.part2 shouldBe 147
    }
  }
}
