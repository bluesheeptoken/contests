import Dependencies._

name := "advent-of-code"
ThisBuild / scalaVersion := "2.13.4"

lazy val root = (project in file("."))
  .aggregate(`advent-of-code-2020`)
  .settings(name := "advent-of-code")

lazy val `advent-of-code-2020` = (project in file("2020"))
  .settings(libraryDependencies += scalaTest % Test)
