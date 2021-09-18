name := """play-scala-seed"""
organization := "com.dynamicguy"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, sbtdocker.DockerPlugin, JavaAppPackaging)
scalaVersion := "2.13.6"


libraryDependencies += guice
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
  }
}


// Set names for the image
docker / imageNames := Seq(
  ImageName("ferdous/play-scala-seed:stable"),
  ImageName(namespace = Some("ferdous"),
    repository = name.value,
    tag = Some("v" + version.value))
)
