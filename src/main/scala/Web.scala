import org.jboss.netty.handler.codec.http.{HttpRequest, HttpResponse}
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.{Http, Response}
import com.twitter.finagle.Service
import com.twitter.util.Future
import java.net.InetSocketAddress
import util.Properties

object Web
{
  def main (args: Array[String])
  {
    ServerBuilder().codec(Http())
                   .name("libot-server")
                   .bindTo(new InetSocketAddress(8080))
                   .build(new Hello)
  }
}

class Hello extends Service[HttpRequest, HttpResponse]
{
  def apply (req: HttpRequest): Future[HttpResponse] = 
  {
    val response = Response()
    response.setStatusCode(200)
    response.setContentString("Hello DiDonato!")
    Future(response)
  }
}