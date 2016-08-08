package com.stratio.clustercanary.actors

import akka.actor.{Actor, Props}
import com.stratio.clustercanary.Messages.EchoedMessage

object Echo {
  def props: Props = Props(new Echo)
}

class Echo extends Actor {

  override def receive: Receive = {
    case msg => sender ! EchoedMessage(sender.path, msg)
  }

}
