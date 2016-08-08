package com.stratio.clustercanary.actors

import akka.actor.{Actor, Props}
import com.stratio.clustercanary.Messages.EchoedMessage
import org.apache.log4j.Logger

object Echo {
  def props: Props = Props(new Echo)
}

class Echo extends Actor {

  private lazy val logger = Logger.getLogger(classOf[Echo])

  override def receive: Receive = {
    case msg =>
      val spath = sender.path
      logger.info(s"RECEIVED MESSAGE: $msg\tFROM: $spath")
      sender ! EchoedMessage(spath, msg)
  }

}
