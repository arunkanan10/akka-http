//import sbt.Keys._
import sbt._


name := "akka-http-helloworld"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-http-testkit-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.11-RC1",
  "org.apache.kafka" % "kafka_2.10" % "0.10.0.1",
  "org.apache.kafka" % "kafka-clients" % "0.10.0.1",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)