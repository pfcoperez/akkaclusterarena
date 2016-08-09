package com.stratio.clustercanary

import akka.util.Timeout
import akka.pattern.ask
import akka.actor.{ActorPath, ActorSystem}
import akka.cluster.client.ClusterClient.SendToAll
import akka.cluster.client.{ClusterClient, ClusterClientSettings}
import com.stratio.clustercanary.Messages.EchoedMessage

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.duration._
import scala.annotation.tailrec
import scala.concurrent.Await

object ClientApp extends App {

  def serverAddress2ActorPath(address: String): ActorPath =
    ActorPath.fromString(s"akka.tcp://CanaryFlock@$address/system/receptionist")


  val system = ActorSystem("ClientSystem")

  val defaultContactPoint = serverAddress2ActorPath("127.0.0.1:7891")

  val initialContacts = if(args.length>0) args.toSet map (serverAddress2ActorPath) else Set(defaultContactPoint)

  val clusterClientActor = system.actorOf(
    ClusterClient.props(ClusterClientSettings(system).withInitialContacts(initialContacts)),
    "clusterClientActor"
  )

  @tailrec
  def forEverSend(stopAt: Option[Int] = None)(n: Int = 0): Unit = {

    val within = 500 milliseconds
    implicit val _: Timeout = within

    val msg = s"hello from client $n"

    print(s"Sent: $msg")

    val resFut = clusterClientActor ? SendToAll("/user/echo_cave", msg) collect {
      case EchoedMessage(_, msg) => msg
    }

    assert(
      Await.result(resFut, within) == msg,
      s"""Wrong echo received: "$msg" at step $n!"""
    )

    println("...successfully echoed.")

    if(stopAt.map(_ > n).getOrElse(true)) forEverSend(stopAt)(n+1)
  }

  forEverSend(Some(5))()



}
