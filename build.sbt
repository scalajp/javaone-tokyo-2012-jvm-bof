import AssemblyKeys._

name := "javaone-tokyo-2012-jvm-bof"

version := "0.1"

scalaVersion := "2.9.1"

resolvers ++= Seq(
  "twitter-repo" at "http://maven.twttr.com",
  "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools/"
)

libraryDependencies ++= Seq(
  "thrift" % "libthrift" % "0.5.0" from "http://maven.twttr.com/org/apache/thrift/libthrift/0.5.0/libthrift-0.5.0.jar",
  "org.scalaj" %% "scalaj-http" % "0.3.0",
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.3.0",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.3.0",
  "com.twitter" %% "util" % "2.0.0",
  "org.specs2" %% "specs2" % "1.8" % "test"
)

seq(assemblySettings: _*)
