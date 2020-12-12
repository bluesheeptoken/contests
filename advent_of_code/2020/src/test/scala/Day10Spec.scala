import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day10Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "the chain that uses all adaptors" in {
      Day10.part1 shouldBe 2590
    }
  }

  "part2" should {
    "count the number of distincts chains of adaptors" in {
      Day10.part2 shouldBe 226775649501184L
    }
  }
}
