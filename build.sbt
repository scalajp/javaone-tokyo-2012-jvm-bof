import AssemblyKeys._

name := "javaone-tokyo-2012-jvm-bof"

version := "0.1"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "twitter-repo" at "http://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "com.twitter" %% "util" % "1.12.13",
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.3.0",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.3.0",
  "org.specs2" %% "specs2" % "1.8" % "test"
)

seq(assemblySettings: _*)
