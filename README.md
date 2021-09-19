play-scala-seed
===============

An example playframework scala app with docker integration using sbt-docker plugin

![home page](public/images/site.png "screenshot")

[![OSSAR](https://github.com/dynamicguy/play-scala-seed/actions/workflows/ossar-analysis.yml/badge.svg)](https://github.com/dynamicguy/play-scala-seed/actions/workflows/ossar-analysis.yml)
[![Scala CI](https://github.com/dynamicguy/play-scala-seed/actions/workflows/scala.yml/badge.svg)](https://github.com/dynamicguy/play-scala-seed/actions/workflows/scala.yml)

### How to push in docker hub
Please make sure you are loggedin in docker hub. Do login `docker login` if necessary.

    sbt dockerBuildAndPush

### How to Run

In SBT, just run `docker:publishLocal` to create a local docker container. 

To run the app in docker, run `docker-compose up`

While running, try opening a new browser tab and visit http://localhost:9000
