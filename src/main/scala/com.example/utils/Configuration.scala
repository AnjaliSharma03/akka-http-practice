package com.example.utils

import com.typesafe.config.{Config, ConfigFactory}

object Configuration {

  val conf: Config = ConfigFactory.load()
  val httpServerConfig: Config = conf.getConfig("http")
  val interface: String = httpServerConfig.getString("interface")
  val port: Int = httpServerConfig.getInt("port")
  val prefix: String = httpServerConfig.getString("prefix")

}
