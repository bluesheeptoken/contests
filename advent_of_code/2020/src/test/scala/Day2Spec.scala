import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day2Spec extends AnyWordSpec with Matchers {
  "is policy respected part1" should {
    "returns true if the number of letters is in the range" in {
      Day2.Policy(1, 3, 'a').isPolicyRespectedPart1("baaab") shouldBe true
      Day2.Policy(1, 3, 'a').isPolicyRespectedPart1("bab") shouldBe true
    }

    "returns false if the number of letters is lower than the range" in {
      Day2.Policy(1, 3, 'a').isPolicyRespectedPart1("b") shouldBe false
    }

    "returns false if the number of letters is higher than the range" in {
      Day2.Policy(1, 3, 'a').isPolicyRespectedPart1("aaaa") shouldBe false
    }
  }

  "is policy respected part2" should {
    "returns true if there is only one letter correct" in {
      Day2.Policy(1, 3, 'a').isPolicyRespectedPart2("abb") shouldBe true
    }

    "returns false if both letters are correct" in {
      Day2.Policy(1, 3, 'a').isPolicyRespectedPart2("aba") shouldBe false
    }

    "returns false if no letters are correct" in {
      Day2.Policy(1, 3, 'a').isPolicyRespectedPart2("bab") shouldBe false
    }
  }

  "part1" should {
    "count number passwords with policy part1 is respected" in {
      Day2.part1 shouldBe 517
    }
  }

  "part2" should {
    "count number passwords with policy part2 is respected" in {
      Day2.part2 shouldBe 284
    }
  }
}
