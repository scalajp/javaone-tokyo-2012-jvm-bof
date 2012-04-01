package org.scala_users.jp.bench

import com.twitter.util.Time
import com.twitter.conversions.time._
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

  def execute(fileName: String, isMutable: Boolean) {
    val map: RBTreeMapI =
      if (isMutable) mutable.RBTreeMap.newInstance
      else immutable.RBTreeMap.newInstance

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
  }
  
  
  def main(args: Array[String]) {
    val file = args(0)
    val isMutable = args.size > 1 && args(1) == "immutable"
    val warmUpStart = Time.now
    do {
      execute(file, isMutable)
    } while (warmUpStart.untilNow < 10.seconds)
    val start = Time.now
    execute(file, isMutable)
    println(start.untilNow.inMilliseconds)
  }
  
}
