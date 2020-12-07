import scala.collection.mutable

object Day7 extends Day[Int, Int] {
  type GroupAnswers = List[String]

  override def part1: Int = {
    def loop(elt: String, visited: Set[String]): Set[String] =
      if (visited.contains(elt))
        visited
      else {
        val nghbs = parents.getOrElse(elt, List.empty).filterNot(visited.contains)
        nghbs.foldLeft(visited + elt)((acc, element) => loop(element, acc))
      }
    loop("shiny gold", Set.empty).size - 1 // Removes shiny gold
  }

  override def part2: Int = {
    // TODO: lru cache
    def loop(rules: Map[String, List[(Int, String)]], s: String): Int =
      rules.get(s) match {
        case Some(l) => l.map { case (qty, color) => qty * (1 + loop(rules, color)) }.sum
        case None    => 0
      }
    loop(children, "shiny gold")
  }

  val (children, parents) = {
    val children: mutable.Map[String, List[(Int, String)]] = mutable.Map.empty
    val parents: mutable.Map[String, List[String]]  = mutable.Map.empty
    lines.toList.foreach { line =>
      val color = "([A-Za-z\\s]+) bags contain ".r
        .findFirstMatchIn(line).getOrElse(throw ParsingException()).group(1)
      val matches = "(\\d+) ([A-Za-z\\s]+) bag".r.findAllMatchIn(line).toList

      val numbersAndColors = matches.map(groups => (groups.group(1).toInt, groups.group(2)))

      children(color) = numbersAndColors.map { case (nb, color) => (nb, color) }
      numbersAndColors.foreach { case (_, c) =>
        if (parents.contains(c)) parents(c) = color :: parents(c) else parents(c) = List(color)
      }
    }
    (children.toMap, parents.toMap)
  }
}
