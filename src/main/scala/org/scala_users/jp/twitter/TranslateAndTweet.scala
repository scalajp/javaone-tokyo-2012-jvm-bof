package org.scala_users.jp.twitter

import scalax.io._
import java.io.Reader
import scalaj.http.HttpOptions._
import scalaj.http.{Token, Http}
import org.scala_users.jp.twitter.ExFunction._

class ExFunction[-ARG, +RET](f: ARG => RET) {
  def :>:[A](g: A => ARG): A => RET = f compose g
  def :*:(arg: ARG): RET = f(arg)
}
object ExFunction {
  implicit def funtionToEx[A, B](f: A => B) = new ExFunction(f)
}

object TranslateAndTweet {

  def main(args: Array[String]) {
    Console.in :*: read :>: translate :>: addHashTag :>: tweet
  }

  val read: Reader => String = { in =>
    Resource.fromReader(in).lines().mkString("\n")
  }

  val translate: String => String = { in =>
    val appId = "Bing App ID"
    Http("https://api.microsofttranslator.com/v2/Http.svc/Translate")
      .options(connTimeout(1000), readTimeout(5000))
      .params("appId" -> appId, "text" -> in, "from" -> "en", "to" -> "ja")
      .asXml
      .text
  }

  val addHashTag: String => String = _ + " #javaonetokyo_bof"

  val tweet: String => Unit = { msg =>
    val consumer = Token("Consumer Key", "Consumer Secret")
    val accessToken = Token("Access Token", "Access Token Secret")
    Http.post("https://api.twitter.com/1/statuses/update.xml")
      .options(connTimeout(1000), readTimeout(5000))
      .params("status" -> msg)
      .oauth(consumer, accessToken)
      .asXml
  }

}
class TranslateAndTweet
