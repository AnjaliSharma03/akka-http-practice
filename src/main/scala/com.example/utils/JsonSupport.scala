package com.example.utils

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.example.routes.calculator.{CalculatorRequest, CalculatorResponse}
import com.example.routes.hello.HelloResponse
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val helloRequestFormat: RootJsonFormat[HelloResponse] = jsonFormat2(
    HelloResponse
  )
  implicit val calculatorRequestFormat: RootJsonFormat[CalculatorRequest] =
    jsonFormat2(CalculatorRequest)
  implicit val calculatorResponseFormat: RootJsonFormat[CalculatorResponse] =
    jsonFormat1(CalculatorResponse)

}
