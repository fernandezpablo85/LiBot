package com.linkedin.libot.model

import com.linkedin.libot.api.Login

object AwaitingVerifierState extends State
{
  override def handle (args : Map[String, String]) =
  {
    try
    {
      val user = getUser(args)
      val verifier = getMessage(args)
      Login.authorize(user, verifier)
      StateRegistry.update(user, InitialState)
      "You're now authorized! :)"
    }
    catch
    {
      case e: Exception => "There was an error trying to authorize you :("
    }

  }
}