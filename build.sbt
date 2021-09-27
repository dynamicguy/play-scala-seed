import play.core.PlayVersion.akkaVersion

name := """play-scala-seed"""
organization := "com.dynamicguy"
maintainer := "Nurul Ferdous <nurul@ferdo.us>"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, sbtdocker.DockerPlugin, JavaAppPackaging)

scalaVersion := "2.13.6"
// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-encoding", "UTF-8",
  "-Xlint",
)

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "com.h2database" % "h2" % "1.4.200"
libraryDependencies += "org.webjars" %% "webjars-play" % "2.8.8"
libraryDependencies += "org.webjars" % "flot" % "0.8.3-1"
libraryDependencies += "org.webjars" % "bootstrap" % "5.1.0"
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "6.2"
libraryDependencies += "org.jsoup" % "jsoup" % "1.14.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
libraryDependencies += "com.rabbitmq" % "amqp-client" % "5.13.1"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.dynamicguy.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.dynamicguy.binders._"
docker / dockerfile := {
  val appDir: File = stage.value
  val targetDir = "/app"

  new Dockerfile {
    from("openjdk:11-jre")
    expose(9000)
    entryPoint(s"$targetDir/bin/${executableScriptName.value}")
    copy(appDir, targetDir, chown = "daemon:daemon")
    runRaw("")
  }
}

// Set names for the image
docker / imageNames := Seq(
  ImageName("ferdous/play-scala-seed:latest"),
  ImageName(namespace = Some("ferdous"),
    repository = name.value,
    tag = Some("v" + version.value))
)

docker / buildOptions := BuildOptions(cache = false)