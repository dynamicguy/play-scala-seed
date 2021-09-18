play-scala-seed
===============

An example playframework scala app with docker integration using sbt-docker plugin

### How to push in docker hub

    sbt dockerBuildAndPush

### How to Run

In SBT, just run `docker:publishLocal` to create a local docker container. 

To run the cluster, run `docker-compose up`

While running, try opening browser tab and visit http://localhost:9000
