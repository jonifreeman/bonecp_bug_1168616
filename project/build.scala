import sbt._
import Keys._

object BenchmarkBuild extends Build {
  lazy val basicSettings = Defaults.defaultSettings ++ Seq(
    organization := "com.example",
    version := "0.1",
    scalaVersion := "2.10.2",
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    javacOptions ++= Seq("-target", "1.6", "-source", "1.6"),
    crossPaths := false,
    libraryDependencies ++= Seq(
      "com.jolbox" % "bonecp" % "0.7.1.RELEASE",
      //"com.jolbox" % "bonecp" % "0.8.0-alpha1",
      "postgresql" % "postgresql" % "9.1-901.jdbc4",
      "org.slf4j" % "slf4j-simple" % "1.6.4"
    )
  )

  lazy val benchmark = Project(
    id = "benchmark",
    base = file("."),
    settings = basicSettings
  )
}
