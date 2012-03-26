package org.scala_users.jp.dsl

import org.specs2._
import java.util.Properties

class HierarchyMapSpec extends Specification { def is =

  "This is a spec to check the HierarchyMap"                                  ^
                                                                              p^
  "The get method should"                                                     ^
    `return None when the HierarchyMap is empty`                              ^
    `return a value related with the key`                                     ^
    `return None when the HierarchyMap does not contain the key`              ^
                                                                              endp^
  "The put method should"                                                     ^
    `relate the key to the new value`                                         ^
                                                                              endp^
  "The toProperties method should"                                            ^
    `return a java.util.Properties`                                           ^
                                                                              end

  val target = HierarchyMap(
    "compiler.error.message.varNotFound" -> "a{1}",
    "compiler.error.message.incompatibleType" -> "b",
    "compiler.error.maxReport" -> "c",
    "compiler.files" -> "d",
    "compiler.files.input.encoding" -> "e",
    "compiler.files.output.encoding" -> "f"
  )

  def `return None when the HierarchyMap is empty` = HierarchyMap.empty.get("aa") must beNone
  def `return a value related with the key` = target.get("compiler.files.input.encoding") must beSome.which { "e" == }
  def `return None when the HierarchyMap does not contain the key` = target.get("compiler.error") must beNone

  def `relate the key to the new value` = {
    val newTarget = target + ("compiler.error.maxReport" -> "xxx")
    newTarget.get("compiler.error.maxReport") must beSome.which { "xxx" == }
  }

  def `return a java.util.Properties` = target.toProperties must beEqualTo {
    val p = new Properties
    p.setProperty("compiler.error.message.varNotFound", "a{1}")
    p.setProperty("compiler.error.message.incompatibleType", "b")
    p.setProperty("compiler.error.maxReport", "c")
    p.setProperty("compiler.files", "d")
    p.setProperty("compiler.files.input.encoding", "e")
    p.setProperty("compiler.files.output.encoding", "f")
    p
  }
}
