package org.scala_users.jp.dsl

import scala.util.parsing.combinator._

object external extends RegexParsers {

  def term: Parser[String] = """([\w.]|\\\{|\\\})+""".r ^^ { case v => v.filter('\\'!=) }

  def unit: Parser[HierarchyMap[String]] = term~"="~term ^^ {
    case (k~_~v) => HierarchyMap(k -> v)
  }

  def value: Parser[HierarchyMap[String]] = unit | term ^^ { case v => new HierarchyMap(Some(v), Map.empty) }

  def prefixWith: Parser[HierarchyMap[String]] = term~"{"~rep(prefixWith | value)~"}" ^^ {
    case prefix~_~v~_ => prefix :+: v.reduceLeft(_ ++ _)
  }

  def all: Parser[HierarchyMap[String]] = rep(prefixWith | value) ^^ {
    case v => v.reduceLeft(_ ++ _)
  }

}
