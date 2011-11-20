package com.linkedin.libot.model

import com.linkedin.libot.api.Login

/**
 * @author: Pablo Fernandez
 */

abstract class AuthorizedState(arguments : Map[String, String]) extends InitialState(arguments)
{
  override def transition =
  {
    if (Login.isAuthorized(user)) super.transition else new UnauthorizedState(arguments)
  }
}