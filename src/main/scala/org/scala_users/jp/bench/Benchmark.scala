package org.scala_users.jp.bench

import com.twitter.util.Time
import java.io.{InputStreamReader, FileInputStream, BufferedReader}

object Benchmark extends App {

  val start = Time.now

  val map = mutable.RBTreeMap.newInstance

  def using(f: Stream[String] => Unit) {
    val in = new FileInputStream(args(0))
    try {
      val reader = new InputStreamReader(in, "UTF-8")
      val buff = new BufferedReader(reader)
      val stream = Stream.continually(buff.readLine()).takeWhile(null !=)
      f(stream)
    } finally {
     in.close() 
    }
  }

  using { stream =>
    for {
      line <- stream
      Array(key, value, height, _*) = line.split(',')
    } {
      map.put(key, value)
      //    assert(height.toInt == map.height)
    }
  }

  using { stream =>
    for {
      line <- stream
      Array(key, value, height, _*) = line.split(',')
    } {
      val actual = map.get(key)
      assert(value == actual)
    }
  }

  println(start.untilNow.inMilliseconds)
  
}
