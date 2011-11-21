package com.linkedin.libot.model

import com.linkedin.libot.api.Login

case class AuthorizeState(context : Map[String, String]) extends State(context)
{
  override def answer =
  {
    try
    {
      Login.authorize(user, context.get("verifier").get)
      "You're now authorized!"
    }
    catch
    {
      case e: Exception => "There was an error trying to authorize you :("
    }

  }
}