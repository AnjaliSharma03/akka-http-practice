package com.example.services

import com.example.routes.calculator.{CalculatorRequest, CalculatorResponse}

object CalculatorService {
  def calculate(calculatorRequest: CalculatorRequest): CalculatorResponse = {
    {
      calculatorRequest.operation match {
        case "+" if calculatorRequest.operands.size > 1 =>
          val result = calculatorRequest.operands.sum.toString
          CalculatorResponse(result)
        case "-" if calculatorRequest.operands.size > 1 =>
          val result = substract(calculatorRequest.operands).toString
          CalculatorResponse(result)
        case "*" if calculatorRequest.operands.size > 1 =>
          val result = calculatorRequest.operands.product.toString
          CalculatorResponse(result)
        case "/" if calculatorRequest.operands.size > 1 =>
          val result = divide(calculatorRequest.operands).toString
          CalculatorResponse(result)
        case _ =>
          CalculatorResponse("Not a valid request")
      }
    }
  }

  def substract(operators: List[Int]): Int = {
    @scala.annotation.tailrec
    def subtractRec(operators: List[Int], result: Int): Int = operators match {
      case _ :: tail if tail.nonEmpty =>
        subtractRec(tail, result - tail.head)
      case _ :: tail if tail.isEmpty => result
    }

    subtractRec(operators, operators.head)
  }

  def divide(operators: List[Int]): Double = {
    @scala.annotation.tailrec
    def divideRec(operators: List[Int], result: Int): Int = operators match {
      case head :: _ if head == 0     => throw new Exception("Cannot divide")
      case _ :: tail if tail.nonEmpty => divideRec(tail, result / tail.head)
      case _                          => result
    }

    divideRec(operators, operators.head)
  }
}
