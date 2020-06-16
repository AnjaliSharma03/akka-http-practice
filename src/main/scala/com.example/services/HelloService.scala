package com.example.services

import com.example.routes.hello.HelloResponse

object HelloService {
  def displayMessage(name: String, message: String): HelloResponse = {
    HelloResponse(name, message)
  }
}
