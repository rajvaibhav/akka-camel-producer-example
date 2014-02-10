package com.example;

import akka.actor.ActorRef;
import akka.camel.javaapi.UntypedProducerActor;
 
/**
  * Actor class definition for forwarding the REST resources requests.
  */
public class Forwarder extends UntypedProducerActor {
  private String uri;
 
  public Forwarder(String uri, ActorRef target) {
    this.uri = uri;
  }
 
  public String getEndpointUri() {
    return uri;
  }
}
