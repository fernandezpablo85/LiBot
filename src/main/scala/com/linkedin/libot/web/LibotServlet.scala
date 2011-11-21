package com.linkedin.libot.web

import com.twitter.finagle.Service
import org.jboss.netty.handler.codec.http.{HttpResponse, HttpRequest}
import com.twitter.util.Future
import com.linkedin.libot.util.ParamsExtractor
import com.linkedin.libot.model.Dispatcher
import com.twitter.finagle.http.Response._
import com.twitter.util.Future._
import com.twitter.finagle.http.Response

/**
 * @author: Pablo Fernandez
 */

class LibotServlet extends Service[HttpRequest, HttpResponse]
{
  def apply (req: HttpRequest): Future[HttpResponse] =
  {
    val rawInput = new String(req.getContent.array, "UTF-8")
    val params = ParamsExtractor.extract(rawInput)
    val responseMessage = Dispatcher.handle(params)
    val response = Response()
    response.setStatusCode(200)
    response.setContentString(responseMessage.toString)
    Future(response)
  }
}
