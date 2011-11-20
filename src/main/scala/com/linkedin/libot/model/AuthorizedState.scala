package com.linkedin.libot.model

import com.linkedin.libot.api.Login

/**
 * @author: Pablo Fernandez
 */

trait AuthorizedState
{
  self : InitialState =>

  abstract override def transition: State =
  {
    if (Login.isAuthorized(user)) self.transition else new UnauthorizedState(arguments)
  }
}