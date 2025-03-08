ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.5"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "2.0.0"

lazy val root = (project in file("."))
  .settings(
    name := "mse-advprog-csv-data"

  )
