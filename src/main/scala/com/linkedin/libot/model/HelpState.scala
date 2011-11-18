package com.linkedin.libot.model

/**
 * @author: Alejandro Bologna
 */

class HelpState(term: String) extends InitialState
{
  override def answer: String =
  {
    "Need some help on " + term + " huh? Try with 'find John'\nIt's all I have for now..."
  }
}