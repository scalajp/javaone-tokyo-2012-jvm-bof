package org.scala_users.jp.bench

import com.twitter.util.Time
import scalax.io.Resource

object Benchmark extends App {

  val start = Time.now

  val map = RBTreeMap.newInstance

  for {
    line <- Resource.fromClasspath("rbtree_map_input.csv").lines()
    Array(key, value, height, _*) = line.split(',')
  } {
    map.put(key, value)
//    assert(height.toInt == map.height)
  }

  for {
    line <- Resource.fromClasspath("rbtree_map_input.csv").lines()
    Array(key, value, height, _*) = line.split(',')
  } {
    val actual = map.get(key)
    assert(value == actual)
  }

  println(start.untilNow.inMilliseconds)

}
