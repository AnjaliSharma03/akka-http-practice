package com.example.routes.calculator

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.example.services.CalculatorService
import com.example.utils.JsonSupport
import scala.concurrent.duration._

object CalculatorRoute extends JsonSupport {
  val route: Route = {
    post {
      path("calculate") {
        entity(as[CalculatorRequest]) { request =>
          {
            val result = CalculatorService.calculate(request)
            complete(result)
          }
        }
      }
    }
  }
  implicit lazy val timeout: Timeout = Timeout(100.seconds)
}
