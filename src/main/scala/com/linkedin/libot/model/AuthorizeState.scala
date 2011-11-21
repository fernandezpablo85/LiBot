package com.linkedin.libot.model

import com.linkedin.libot.api.Login

class AuthorizeState(arguments : Map[String, String]) extends InitialState(arguments) {

  override def answer =
  {
    Login.authorize(arguments.get("userkey").get, arguments.get("msg").get)
    "You're now authorized!"
  }
}