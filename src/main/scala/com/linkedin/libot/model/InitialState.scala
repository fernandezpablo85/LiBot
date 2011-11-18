package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

object InitialState extends State
{
  def answer (input: String): String =
  {
    UnknownMessageGenerator.next
  }

  def transition (input: String) =
  {
    input match
    {
      case _ => InitialState
    }
  }
}