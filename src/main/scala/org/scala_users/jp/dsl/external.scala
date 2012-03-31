package org.scala_users.jp.dsl

import scala.util.parsing.combinator._

object external extends RegexParsers with ToProperties {

  def key: Parser[String] = """[\w.]+""".r

  def value: Parser[String] = """[^\s][^\r\n]*""".r

  def pair: Parser[Map[String, String]] = key~"="~value ^^ {
    case key~_~value => Map(key -> value)
  }

  def unit: Parser[Map[String, String]] = "="~>value ^^ {
    case value => Map("" -> value)
  }

  def properties: Parser[Map[String, String]] = rep(unit | pair | nested) ^^ {
    _.flatten.toMap
  }

  def nested: Parser[Map[String, String]] = key~("{"~>properties<~"}") ^^ {
    case key~values => values.map {
      case ("", v) => (key, v)
      case (k, v)  => (key + "." + k, v)
    }
  }

}
