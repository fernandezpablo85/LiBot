package com.linkedin.libot.model

import com.linkedin.libot.api.Login

/**
 * @author: Pablo Fernandez
 */

class UnauthorizedState(arguments : Map[String, String]) extends InitialState(arguments)
{
  val url = Login.getAuthorizationUrl(user)
  override def answer = "must authorize here: "+url

  override def transition =
  {
    message match {
      case verifier : String => {
        Login.authorize(user, verifier)
        new InitialState(arguments) { override def answer = "Cool! now you're authorized :)"}
      }
    }
  }
}