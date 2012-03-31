package org.scala_users.jp.dsl

import java.util.Properties
import java.io.OutputStream

trait ToProperties {

  implicit def mapToProperties(map: Map[String, String]) = new {

    def toProperties: Properties = map.foldLeft(new Properties) {
      case (p, (key, value)) => p.setProperty(key, value); p
    }

    def store(out: OutputStream, comments: String = null) {
      toProperties.store(out, comments)
    }

  }

}
