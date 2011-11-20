package com.linkedin.libot.model

/**
 * @author: Alejandro Bologna
 */

class HelpState(arguments: Map[String, String]) extends InitialState(arguments)
{
  override def answer: String =
  {
    "Need some help on " + message + " huh? Try with 'find John'\nIt's all I have for now..."
  }
}