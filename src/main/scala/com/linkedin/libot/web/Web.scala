package com.linkedin.libot.web

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.{Response, Http}
import com.twitter.finagle.Service
import com.twitter.util.Future
import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse}
import java.net.InetSocketAddress
import util.Properties
import com.linkedin.libot.model.Dispatcher

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

class LibotServlet extends Service[HttpRequest, HttpResponse]
{
  def apply (req: HttpRequest): Future[HttpResponse] = 
  {
    val input = new String(req.getContent.array, "UTF-8")
    val message = Dispatcher.handle(input)

    val response = Response()
    response.setStatusCode(200)
    response.setContentString(message.toString)
    Future(response)
  }
}
