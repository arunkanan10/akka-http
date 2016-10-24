package com.madhukaraphatak.akkahttp

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.kafka.ProducerSettings
import org.apache.kafka.clients.producer.ProducerRecord
import akka.http.scaladsl.server.Directives._
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}
import akka.stream.scaladsl._
import akka.kafka.scaladsl.Producer

/**
  * Created by madhu on 8/11/15.
  */
object AkkaHttpHelloWorld {

  def main(args: Array[String]) {

    implicit val actorSystem = ActorSystem("system")
    implicit val actorMaterializer = ActorMaterializer()

    val producerSettings = ProducerSettings(actorSystem, new ByteArraySerializer, new StringSerializer)
                                .withBootstrapServers("localhost:9092")
                                
  val done = Source(1 to 100)
    .map(_.toString)
    .map { elem =>
      new ProducerRecord[Array[Byte], String]("topic1", elem)
    }
    .runWith(Producer.plainSink(producerSettings))

    val route =
      pathSingleSlash {
        get {
          complete {
            "Hello Arun!!!"
          }
        }
      } ~
        pathPrefix("ball") {
          pathSingleSlash {
            complete("/ball/")
          } ~
            path(IntNumber) { int =>
              complete(if (int % 2 == 0) "even ball" else "odd ball")
            }
        }
    Http().bindAndHandle(route,"localhost",8080)

    println("server started at 8080")
  }
}
