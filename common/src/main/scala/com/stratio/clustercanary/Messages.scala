package com.stratio.clustercanary

import akka.actor.ActorPath

object Messages{
  case class EchoedMessage(from: ActorPath, contents: Any)
}
