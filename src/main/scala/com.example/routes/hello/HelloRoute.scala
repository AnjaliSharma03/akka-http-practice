package com.example.routes.hello

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.example.services.HelloService
import com.example.utils.JsonSupport

object HelloRoute extends JsonSupport {
  val route: Route = {
    get {
      path("hello") {
        parameters('name, 'message) { (name, message) =>
          {
            complete(HelloService.displayMessage(name, message))
          }
        }
      }
    }
  }
}
