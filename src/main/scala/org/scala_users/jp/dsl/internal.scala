package org.scala_users.jp.dsl

import scala.util.DynamicVariable

object internal extends ToProperties {

  private[this] val current = new DynamicVariable[String]("")

  private[this] val values = new DynamicVariable[Option[Map[String, String]]](None)

  class Builder(key: String) {

    def := (value: String): Map[String, String] = {
      val currentValues = values.value.getOrElse(Map())
      val newValues = currentValues + (concatKey(key) -> value)
      values.value = Some(newValues)
      newValues
    }

    def := (body: => Any): Map[String, String] = {
      current.withValue(concatKey(key)) {
        values.value match {
          case Some(v) => {
            body
            v
          }
          case None => values.withValue(Some(Map())) {
            body
            values.value.getOrElse(Map())
          }
        }
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