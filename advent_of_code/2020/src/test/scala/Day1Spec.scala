import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day1Spec extends AnyWordSpec with Matchers {
  val numbers = Set(1721, 979, 366, 299, 675, 1456)

  "two sum" should {
    // TODO: Fixme for case n is even and we can sum n + n
    "return two numbers when there is a solution" in {
      Day1.twoSum(numbers, 2020) shouldBe Some((299, 1721))
    }
  }

  "part1" should {
    "find 2 numbers that sum to 2020 and multiply them from input" in {
      Day1.part1 shouldBe 145875
    }
  }

  "part2" should {
    "find 3 numbers that sum to 2020 and multiply them from input" in {
      Day1.part2 shouldBe 69596112
    }
  }
}
