package org.scala_users.jp.dsl

import scala.util.DynamicVariable

object internal extends ToProperties {

  private[this] var values: List[(String, String)] = Nil

  private[this] val current = new DynamicVariable[String]("")

  class Builder(key: String) {
    def := (value: String) {
      values = (concatKey(key), value) :: values
    }

    def := (body: => Any): Map[String, String] = {
      current.withValue(concatKey(key)) {
        body
        values.toMap
      }
    }
    
    def concatKey(key: String): String = (current.value, key) match {
      case ("", "") => ""
      case ("", _)  => key
      case (_, "")  => current.value
      case (_, _)   => current.value + "." + key
    }
  }

  implicit def stringToBuilder(arg: String) = new Builder(arg)

}