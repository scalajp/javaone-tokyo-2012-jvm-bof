package org.scala_users.jp.bench

import com.twitter.util.Time
import java.io.{InputStreamReader, FileInputStream, BufferedReader}

object Benchmark {

  def using(fileName: String)(f: Stream[String] => Any) {
    val in = new FileInputStream(fileName)
    try {
      val reader = new InputStreamReader(in, "UTF-8")
      val buff = new BufferedReader(reader)
      val stream = Stream.continually(buff.readLine()).takeWhile(null !=)
      f(stream)
    } finally {
     in.close() 
    }
  }

  def execute(fileName: String) {
    val start = Time.now

    val map = mutable.RBTreeMap.newInstance

    using(fileName) { stream =>
      for {
        line <- stream
        Array(key, value, height, _*) = line.split(',')
      } {
        map.put(key, value)
        //    assert(height.toInt == map.height)
      }
    }

    using(fileName) { stream =>
      for {
        line <- stream
        Array(key, value, height, _*) = line.split(',')
      } {
        val actual = map.get(key)
        assert(value == actual)
      }
    }

    start.untilNow.inMilliseconds
  }
  
  
  def main(args: Array[String]) {
    val file = args(0)
    1 to 5 foreach { _ =>
      execute(file)
    }
    val start = Time.now
    execute(file)
    println(start.untilNow.inMilliseconds)
  }
  
}
