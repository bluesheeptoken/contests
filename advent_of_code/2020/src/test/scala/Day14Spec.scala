import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day14Spec extends AnyWordSpec with Matchers {
  "part1" should {
    "sum all values in memory" in {
      Day14.part1 shouldBe 4297467072083L
    }
  }

  "part2" should {
    "sum all values in memory" in {
      Day14.part2 shouldBe 5030603328768L
    }
  }
}
