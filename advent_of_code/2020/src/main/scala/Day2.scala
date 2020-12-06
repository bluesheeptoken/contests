object Day2 extends Day[Int, Int] {
  final case class Policy(a: Int, b: Int, letter: Char) {
    def isPolicyRespectedPart1(password: String): Boolean = {
      val count = password.count(_ == letter)
      a <= count && count <= b
    }

    def isPolicyRespectedPart2(password: String): Boolean =
      password.charAt(a - 1) == letter ^ password.charAt(b - 1) == letter
  }
  override def part1: Int = policiesAndPasswords.count { case (policy, pwd) =>
    policy.isPolicyRespectedPart1(pwd)
  }

  override def part2: Int = policiesAndPasswords.count { case (policy, pwd) =>
    policy.isPolicyRespectedPart2(pwd)
  }

  private val policiesAndPasswords: List[(Policy, String)] = lines.toList.map {
    case s"$a-$b $letter: $pwd" => (Policy(a.toInt, b.toInt, letter.head), pwd)
  }
}
