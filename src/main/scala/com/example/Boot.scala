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

  val camel = CamelExtension(system)
  val camelContext = camel.context
  
  camelContext.addComponent("jetty", new org.apache.camel.component.jetty.JettyHttpComponent())
  val camelProducer = system.actorOf(Props(classOf[Forwarder], "http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html", service), "remoteRestServer")

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(service, "localhost", port = 8080)
}
