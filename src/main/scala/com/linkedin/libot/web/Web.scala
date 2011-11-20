package com.linkedin.libot.web

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.Http
import java.net.InetSocketAddress
import util.Properties

object Web
{
  val DEVELOPMENT_PORT = "8080"

  def main (args: Array[String])
  {
    val port = Properties.envOrElse("PORT", DEVELOPMENT_PORT).toInt
    ServerBuilder().codec(Http())
                   .name("libot-server")
                   .bindTo(new InetSocketAddress(port))
                   .build(new LibotServlet)
  }
}