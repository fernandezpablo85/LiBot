package com.linkedin.libot.api

import org.scribe.model.{Verb, OAuthRequest, Token}

/**
 * @author: Pablo Fernandez
 */

object PeopleSearchApi
{
  def findByName(userId : String, name : String) =
  {
    val request = new OAuthRequest(Verb.GET, "http://api.linkedin.com/v1/people-search?keywords=%s".format(name))

    Login.sign(request, userId)

    val response = request.send()

    response.getBody
  }
}