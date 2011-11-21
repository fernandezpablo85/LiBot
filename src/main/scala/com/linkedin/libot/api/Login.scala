package com.linkedin.libot.api

import scala.collection.JavaConversions._
import java.util.concurrent.{ConcurrentHashMap, ConcurrentMap}
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.LinkedInApi
import com.sun.tools.internal.ws.wscompile.AuthInfo
import org.scribe.model.{OAuthRequest, Verifier, Token}
import java.lang.IllegalStateException

/**
 * @author: Pablo Fernandez
 */
object Login
{
  private val Auth: ConcurrentMap[String, AuthInfo] = new ConcurrentHashMap[String, AuthInfo]

  // TODO: Change this to use our own key.
  private val service = new ServiceBuilder().provider(classOf[LinkedInApi])
                                            .apiKey("CiEgwWDkA5BFpNrc0RfGyVuSlOh4tig5kOTZ9q97qcXNrFl7zqk-Ts7DqRGaKDCV")
                                            .apiSecret("dhho4dfoCmiQXrkw4yslork5XWLFnPSuMR-8gscPVjY4jqFFHPYWJKgpFl4uLTM6")
                                            .build();

  def getAuthorizationUrl(id : String) =
  {
    println("Getting token for: "+id)
    val token = service.getRequestToken
    val authInfo = new AuthInfo(userId = id, requestToken = token)
    Auth.put(id, authInfo)

    println("Got Token:"+token)
    service.getAuthorizationUrl(token);
  }

  def isAuthorized(userId : String) = (Auth.get(userId) !=null && Auth.get(userId).accessToken != null)

  def authorize(userId : String, verifier : String) : Token =
  {
    println("Authorizing: "+userId+" with verifier: "+verifier)
    val authInfo = Auth.get(userId)
    assert(authInfo != null)
    assert(authInfo.requestToken != null)

    println("Request Token is: "+authInfo.requestToken)
    if (authInfo.accessToken != null)
    {
      return authInfo.accessToken;
    }
    else
    {
      val token = service.getAccessToken(authInfo.requestToken, new Verifier(verifier))
      authInfo.accessToken = token
      Auth.put(userId, authInfo)

      token
    }
  }

  def sign(request : OAuthRequest, id : String)
  {
    val token = Auth.get(id)
    assume(token == null || token.accessToken == null, "User not authorized, can't sign request.")

    service.signRequest(token.accessToken, request)
  }

  private class AuthInfo(var userId : String = null,
                         var requestToken : Token = null,
                         var accessToken : Token = null)

}