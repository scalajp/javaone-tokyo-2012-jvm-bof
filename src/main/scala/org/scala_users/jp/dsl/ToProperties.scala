package org.scala_users.jp.dsl

import java.util.Properties

trait ToProperties {

  implicit def mapToProperties(map: Map[String, String]) = new {
    def toProperties: Properties = map.foldLeft(new Properties) {
      case (p, (key, value)) => p.setProperty(key, value); p
    }
  }

}
