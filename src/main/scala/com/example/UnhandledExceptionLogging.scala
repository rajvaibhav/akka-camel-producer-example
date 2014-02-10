package com.example

import akka.actor._

trait UnhandledExceptionLogging{
  self: Actor with ActorLogging =>

  override def preRestart(reason:Throwable, message:Option[Any]){
    log.error(reason, "Unhandled exception for message: {}", message)
  }
}
