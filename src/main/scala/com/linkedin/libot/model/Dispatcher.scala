package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

object Dispatcher
{
  def handle(input : String) =
  {
    input match
    {
      case _ => UnknownMessage(input)
    }
  }
}