package com.example;

import akka.actor.ActorRef;
import akka.camel.javaapi.UntypedProducerActor;
import akka.camel.*;
import akka.dispatch.Mapper;
import akka.event.Logging;
import akka.event.LoggingAdapter;
 
/**
  * Actor class definition for forwarding the REST resources requests.
  */
public class Forwarder extends UntypedProducerActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  private String uri;
  private ActorRef target;
  public Forwarder(String uri, ActorRef target) {
    this.uri = uri;
    this.target = target;
  }

  private String getBodyAsString(CamelMessage msg) {
    return msg.getBodyAs(String.class, (CamelExtension.get(getContext().system())).context());
  }
 
  @Override
  public Object onTransformOutgoingMessage(Object message) {
    if(message instanceof CamelMessage) {
      CamelMessage camelMessage = (CamelMessage) message;
      return getBodyAsString(camelMessage);
    } else {
      return message;
    }
  }
 
  public String getEndpointUri() {
    return uri;
  }

  public ActorRef getTarget() {
    return target;
  }
}
