package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

class InitialState extends RequestState
{
  def handle (input: String) =
  {
    input match
    {
      case _ =>  (UnknownMessageGenerator.next, new InitialState)
    }
  }
}