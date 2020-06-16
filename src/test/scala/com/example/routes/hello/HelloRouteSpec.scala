package com.example.routes.hello

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FlatSpec, Matchers}

class HelloRouteSpec extends FlatSpec with ScalatestRouteTest with Matchers {

  it should "return hello message" in {
    Get("/hello?name=Anjali&message=Welcome") ~> HelloRoute.route ~> check {
      responseAs[String] shouldEqual """{"message":"Welcome","name":"Anjali"}""".stripMargin
    }
  }

}
