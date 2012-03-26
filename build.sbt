import AssemblyKeys._

name := "javaone-tokyo-2012-jvm-bof"

version := "0.1"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "twitter-repo" at "http://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "thrift" % "libthrift" % "0.5.0" from "http://maven.twttr.com/org/apache/thrift/libthrift/0.5.0/libthrift-0.5.0.jar",
  "com.twitter" %% "util" % "1.12.13",
  "org.specs2" %% "specs2" % "1.8" % "test"
)

seq(assemblySettings: _*)
