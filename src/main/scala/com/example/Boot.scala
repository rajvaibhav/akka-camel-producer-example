package com.example

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.camel._


object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  // create and start our service actor
  val service = system.actorOf(Props[DemoServiceActor], "demo-service")

  val camelProducer = system.actorOf(Props(classOf[Forwarder], "GET https://www.googleapis.com/language/translate/v2?key=INSERT-YOUR-KEY&source=en&target=de&q=Hello%20world", null), "remoteRestServer")

  val camel = CamelExtension(system)
  val camelContext = camel.context
  camelContext.addComponent("jetty", new org.apache.camel.component.jetty.JettyHttpComponent())

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(service, "localhost", port = 8080)
}
