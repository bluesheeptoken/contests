import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day5Spec extends AnyWordSpec with Matchers {

  "seatId" should {
    "357 for FBFBBFFRLR" in {
      Day5.seatId("FBFBBFFRLR".toList) shouldBe 357
    }
  }

  "part1" should {
    "find the maximum seatId from inputs" in {
      Day5.part1 shouldBe 951
    }
  }

  "part2" should {
    "find the empty seat from inputs" in {
      Day5.part2 shouldBe 653
    }
  }

}
