package com.example

import java.io.File
import org.parboiled.common.FileUtils
import scala.concurrent.duration._
import akka.actor._
import akka.pattern.ask
import spray.routing.{HttpService, RequestContext}
import spray.routing.directives.CachingDirectives
import spray.can.server.Stats
import spray.can.Http
import spray.httpx.marshalling.Marshaller
import spray.httpx.encoding.Gzip
import spray.util._
import spray.http._
import MediaTypes._
import CachingDirectives._
import akka.pattern.Patterns
import akka.util._
import spray.httpx._
import spray.json._
import DefaultJsonProtocol._
import scala.concurrent._
// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class DemoServiceActor extends Actor with DemoService with ActorLogging {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing,
  // timeout handling or alternative handler registration
  def receive = runRoute(demoRoute)

  override def getContext = actorRefFactory
}


// this trait defines our service behavior independently from the service actor
trait DemoService extends HttpService with SprayJsonSupport with DefaultJsonProtocol{
  // we use the enclosing ActorContext's or ActorSystem's dispatcher for our Futures and Scheduler
  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout = Timeout(30 seconds)
  def getContext: ActorContext
  val demoRoute = {
    get {
      path("ping") {
        complete{
		val remoteServer = getContext.actorSelection("/user/remoteRestServer")
		println(remoteServer)
		val futureResp = ask(remoteServer, "")
		futureResp.mapTo[String]
	}
      }
    }
  } 
      
}
