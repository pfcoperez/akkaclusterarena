package com.stratio.clustercanary

import akka.actor.ActorSystem
import com.stratio.clustercanary.actors.Echo

object EchoServer extends App {

  val system = ActorSystem("CanaryFlock")

  system.actorOf(Echo.props)

}
