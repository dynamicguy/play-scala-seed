play-scala-seed
===============

[![OSSAR](https://github.com/dynamicguy/play-scala-seed/actions/workflows/ossar-analysis.yml/badge.svg)](https://github.com/dynamicguy/play-scala-seed/actions/workflows/ossar-analysis.yml)
[![Scala CI](https://github.com/dynamicguy/play-scala-seed/actions/workflows/scala.yml/badge.svg)](https://github.com/dynamicguy/play-scala-seed/actions/workflows/scala.yml)

An example playframework scala app with docker integration using sbt-docker plugin

![home page](public/images/site.png "screenshot")

### How to push in docker hub
Please make sure you are loggedin in docker hub. Do login `docker login` if necessary.

    sbt dockerBuildAndPush

### How to Run

In SBT, just run `docker:publishLocal` to create a local docker container. 

To run the app in docker, run `docker-compose up`

While running, try opening a new browser tab and visit http://localhost:9000



FROM node:13.12.0-alpine as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm install react-scripts@3.4.1 -g
COPY . ./
RUN npm run build

# production environment
FROM nginx:stable-alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]


docker run --rm -v ${PWD}:/app -v /app/node_modules -p 8082:8082 npm-alex:latest