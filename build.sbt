name := "akkaclusterarena"
version := "1.0"

val akkaGroupId = "com.typesafe.akka"
val akkaVersion = "2.4.9-RC1"

lazy val commonSettings = Seq(
  organization := "com.stratio",
  scalaVersion := "2.11.8",
  libraryDependencies := Seq(
    akkaGroupId %% "akka-actor" % akkaVersion,
    akkaGroupId %% "akka-cluster" % akkaVersion
  )
)

lazy val root = (project in file(".")).
  aggregate(common, server, client)

lazy val server = project.
  settings(commonSettings:_*).
  dependsOn(common)

lazy val client = project.
  settings(commonSettings:_*).
  dependsOn(common)

lazy val common = project.
  settings(commonSettings:_*)


