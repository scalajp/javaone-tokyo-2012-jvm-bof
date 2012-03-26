package org.scala_users.jp.dsl

import org.specs2._
import java.util.Properties
import org.scala_users.jp.dsl.internal._

class InternalSpec extends Specification { def is =

  "This is a spec to check the Internal DSL"                                  ^
                                                                              p^
  `Internal DSL can parse`                                                    ^
                                                                              end

  val target =
    "compiler" := (
      "error" := (
        "message" := (
          "varNotFound" := "a{1}",
          "incompatibleType" := "b"
          ),
        "maxReport" := "c"
        ),
      "files" := (
        "d",
        "input.encoding" := "e",
        "output.encoding" := "f"
        )
      )

  def `Internal DSL can parse` = target.toProperties must beEqualTo {
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
