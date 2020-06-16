package com.example.main

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.server.Directives.pathPrefix
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.example.utils.{Configuration, JsonSupport}
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.server.Directives._
import com.example.routes.calculator.CalculatorRoute
import com.example.routes.hello.HelloRoute

import scala.concurrent.ExecutionContext.global
import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object HttpServerApp extends App with JsonSupport {

  val conf = ConfigFactory.load()

  implicit val actorSystem: ActorSystem = ActorSystem("hello-service")
  implicit val materializer: ActorMaterializer =
    ActorMaterializer()(actorSystem)
  implicit val logger: LoggingAdapter = Logging(actorSystem, "HelloService")

  implicit val executionContext: ExecutionContextExecutor = global

  val interface = Configuration.interface
  val port = Configuration.port
  val prefix = Configuration.prefix

  val route: Route = {
    HelloRoute.route ~ CalculatorRoute.route
  }

  val prefixedRoutes = pathPrefix(prefix).apply(route)

  val serverBinding = Http().bindAndHandle(prefixedRoutes, interface, port)

  logger.info(s"Server online at http://" + s"$interface" + s":$port")

  StdIn.readLine()
  serverBinding
    .flatMap(_.unbind())
    .onComplete(_ => actorSystem.terminate())
}
