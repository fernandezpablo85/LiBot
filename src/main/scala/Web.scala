import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse}
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.{Http, Response}
import com.twitter.finagle.Service
import com.twitter.util.Future
import java.net.InetSocketAddress
import util.Properties

object Web
{

  val DEVELOPMENT_PORT = "8080";

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
    val body = new String(req.getContent.array())
    val response = Response()
    response.setStatusCode(200)
    response.setContentString(body)
    Future(response)
  }
}