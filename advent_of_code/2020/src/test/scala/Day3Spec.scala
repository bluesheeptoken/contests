import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class Day3Spec extends AnyWordSpec with Matchers {
  val grid = List(
    "..##.......",
    "#...#...#..",
    ".#....#..#.",
    "..#.#...#.#",
    ".#...##..#.",
    "..#.##.....",
    ".#.#.#....#",
    ".#........#",
    "#.##...#...",
    "#...##....#",
    ".#..#...#.#"
  ).map(_.toList)

  "encounter" should {
    "returns 2 for (1, 1)" in {
      Day3.encounter(1, 1, grid) shouldBe 2
    }
    "returns 7 for (3, 1)" in {
      Day3.encounter(1, 1, grid) shouldBe 2
    }
    "returns 3 for (5, 1)" in {
      Day3.encounter(1, 1, grid) shouldBe 2
    }
    "returns 4 for (7, 1)" in {
      Day3.encounter(1, 1, grid) shouldBe 2
    }
    "returns 2 for (1, 2)" in {
      Day3.encounter(1, 1, grid) shouldBe 2
    }
  }

  "part1" should {
    "count the number of time we encounter a tree from inputs with steps (1, 1)" in {
      Day3.part1 shouldBe 61
    }
  }

  "part2" should {
    "count the number of time we encounter a tree from inputs with several steps and multiplay them" in {
      Day3.part2 shouldBe 3154761400L
    }
  }

}
