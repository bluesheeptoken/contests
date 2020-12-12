import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day9Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "compute the encoding key" in {
      Day9.part1 shouldBe 542529149
    }
  }

  "part2" should {
    "sum the least and greatest number from contiguous range that sums to the encoding key" in {
      Day9.part2 shouldBe 75678618
    }
  }
}
