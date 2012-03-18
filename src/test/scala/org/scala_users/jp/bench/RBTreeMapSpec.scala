package org.scala_users.jp.bench

import org.specs2._

class RBTreeMapSpec extends Specification { def is =

  "This is a spec to check the RBTreeMap"                                     ^
                                                                              p^
  "The get method should"                                                     ^
    `return null when the RBTreeMap is empty`                                 ^
    `throw an Exception when the getting key is null`                         ^
    `return a value related with the key`                                     ^
    `return null when the RBTreeMap does not contain the key`                 ^
                                                                              endp^
  "The put method should"                                                     ^
    `throw an Exception when the putting key is null`                         ^
    `relate the key to the new value`                                                 ^
                                                                              endp^
  "The height method should"                                                  ^
    `return 0 when the RBTreeMap is empty`                                    ^
    `return 1 when the RBTreeMap has one entry`                               ^
    `return 1 when the RBTreeMap has two entries`                             ^
    `return 2 when the RBTreeMap has three entries`                           ^
    `return 2 when the RBTreeMap has four entries`                            ^
    `return 2 when the RBTreeMap has five entries`                            ^
                                                                              end

  val empty = RBTreeMap()
  val one   = RBTreeMap("a" -> "1")
  val two   = RBTreeMap("a" -> "1", "b" -> "2")
  val three = RBTreeMap("a" -> "1", "b" -> "2", "c" -> "3")
  val four  = RBTreeMap("a" -> "1", "b" -> "2", "c" -> "3", "d" -> "4")
  val five  = RBTreeMap("a" -> "1", "b" -> "2", "c" -> "3", "d" -> "4", "e" -> "5")

  def `return null when the RBTreeMap is empty` = empty.get("a") must beNull
  def `throw an Exception when the getting key is null` = one.get(null) must throwA[IllegalArgumentException]
  def `return a value related with the key` = two.get("b") must be equalTo "2"
  def `return null when the RBTreeMap does not contain the key` = three.get("xxx") must beNull

  def `throw an Exception when the putting key is null` =
    one.put(null, "a") must throwA[IllegalArgumentException]

  def `relate the key to the new value` = {
    val target = RBTreeMap("a" -> "1", "b" -> "2", "c" -> "3")
    target.get("b") must be equalTo "2"
    target.put("b", "xxx")
    target.get("b") must be equalTo "xxx"
  }

  def `return 0 when the RBTreeMap is empty` = empty.height must be equalTo 0
  def `return 1 when the RBTreeMap has one entry` = one.height must be equalTo 1
  def `return 1 when the RBTreeMap has two entries` = two.height must be equalTo 1
  def `return 2 when the RBTreeMap has three entries` = three.height must be equalTo 2
  def `return 2 when the RBTreeMap has four entries` = four.height must be equalTo 2
  def `return 2 when the RBTreeMap has five entries` = five.height must be equalTo 2

}
