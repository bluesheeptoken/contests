import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

object Day4 extends Day[Int, Int] {
  sealed trait Rule {
    def field: String
    def isValid(passport: Passport): Boolean

  }
  final case class WithinRangeRule(field: String, low: Int, high: Int) extends Rule {
    override def isValid(passport: Passport): Boolean =
      passport.get(field).exists(validateField)

    def validateField(value: String): Boolean =
      value.toIntOption.exists(intValue => low <= intValue && intValue <= high)
  }

  final case class WithinRangeWithSuffixRule(
      field: String,
      suffixes: List[(String, WithinRangeRule)]
  ) extends Rule {

    override def isValid(passport: Day4.Passport): Boolean =
      (for {
        value         <- passport.get(field)
        suffixAndRule <- suffixes.find(x => value.endsWith(x._1))
        (suffix, rule) = suffixAndRule

        strippedValue = value.stripSuffix(suffix)
        if rule.validateField(strippedValue)
      } yield ()).isDefined

  }

  final case class RegexValid(field: String, regex: Regex) extends Rule {
    override def isValid(passport: Passport): Boolean =
      passport.get(field).flatMap(regex.findFirstMatchIn).isDefined
  }

  type Passport = Map[String, String]

  override def part1: Int = passports.count(hasPassportMandatoryFields)

  override def part2: Int = {
    val rules: List[Rule] = List(
      WithinRangeRule("byr", 1920, 2002),
      WithinRangeRule("iyr", 2010, 2020),
      WithinRangeRule("eyr", 2020, 2030),
      WithinRangeWithSuffixRule(
        "hgt",
        List(
          ("cm", WithinRangeRule("hgt", 150, 193)),
          ("in", WithinRangeRule("hgt", 59, 76))
        )
      ),
      RegexValid("hcl", "^#[0-9a-f]{6}$".r),
      RegexValid("ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$".r),
      RegexValid("pid", "^[0-9]{9}$".r)
    )

    passports.count(p => rules.forall(_.isValid(p)))

  }

  def hasPassportMandatoryFields(passport: Passport): Boolean =
    mandatoryFields.forall(passport.contains)

  private val mandatoryFields: List[String] =
    List("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  private val passports: List[Passport] =
    split(lines.toList, "").map(toPassport)

  private def split(lines: List[String], sep: String): List[List[String]] = {
    val parent = new ListBuffer[List[String]]
    val child  = new ListBuffer[String]
    lines.foreach { line =>
      if (line != sep) {
        val _ = child.addOne(line)
      } else {
        val _ = parent.addOne(child.toList)
        child.clear()
      }
    }
    val _ = parent.addOne(child.toList)
    parent.toList
  }

  private def toPassport(lines: List[String]): Passport = {
    val map = mutable.Map[String, String]()
    lines.foreach { line =>
      val kvs = line.split(' ')
      kvs.foreach { kv =>
        val Array(k, v) = kv.split(':')
        map(k) = v
      }
    }
    map.toMap
  }
}
