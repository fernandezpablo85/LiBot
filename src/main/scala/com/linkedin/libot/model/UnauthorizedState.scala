package com.linkedin.libot.model

import com.linkedin.libot.api.Login

/**
 * @author: Pablo Fernandez
 */

case class UnauthorizedState(context : Map[String, String]) extends State(context)
{
  val url = Login.getAuthorizationUrl(user)
  override def answer = "must authorize here: " + url
}