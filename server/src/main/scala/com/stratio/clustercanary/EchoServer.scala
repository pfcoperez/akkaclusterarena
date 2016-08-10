package com.stratio.clustercanary

import akka.actor.ActorSystem
import akka.cluster.Cluster
import akka.cluster.client.ClusterClientReceptionist
import com.stratio.clustercanary.actors.Echo

object EchoServer extends App {

  val system = ActorSystem("CanaryFlock")
  Cluster(system).join(Cluster(system).selfAddress)

  val echoActor = system.actorOf(Echo.props, "echo_cave")

  ClusterClientReceptionist(system).registerService(echoActor)

}
