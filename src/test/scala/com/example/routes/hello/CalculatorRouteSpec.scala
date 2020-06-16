package com.example.routes.hello

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.MessageEntity
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.example.routes.calculator.{CalculatorRequest, CalculatorRoute}
import com.example.utils.JsonSupport
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures._

class CalculatorRouteSpec
    extends FlatSpec
    with ScalatestRouteTest
    with Matchers
    with JsonSupport {

  behavior of "Calculator Routes"

  it should "return calculated result" in {
    val entity: MessageEntity =
      Marshal(CalculatorRequest(List(3, 4), "+")).to[MessageEntity].futureValue
    Post("/calculate").withEntity(entity) ~> CalculatorRoute.route ~> check {
      responseAs[String] shouldEqual "{\"result\":\"7\"}"
    }
  }

  it should "return calculated result subtract" in {
    val OPERAND_1 = 7
    val OPERAND_2 = 4
    val entity: MessageEntity =
      Marshal(CalculatorRequest(List(OPERAND_1, OPERAND_2), "-"))
        .to[MessageEntity]
        .futureValue
    Post("/calculate").withEntity(entity) ~> CalculatorRoute.route ~> check {
      responseAs[String] shouldEqual "{\"result\":\"3\"}"
    }
  }

  it should "return calculated result divide" in {
    val OPERAND_1 = 6
    val OPERAND_2 = 2
    val entity: MessageEntity =
      Marshal(CalculatorRequest(List(OPERAND_1, OPERAND_2), "/"))
        .to[MessageEntity]
        .futureValue
    Post("/calculate").withEntity(entity) ~> CalculatorRoute.route ~> check {
      responseAs[String] shouldEqual "{\"result\":\"3.0\"}"
    }
  }

  it should "return invalid operator" in {
    val invalidEntity = Marshal(CalculatorRequest(List(1, 2), "+-"))
      .to[MessageEntity]
      .futureValue
    Post("/calculate").withEntity(invalidEntity) ~> CalculatorRoute.route ~> check {
      responseAs[String] shouldEqual "{\"result\":\"Not a valid request\"}"
    }
  }

}
