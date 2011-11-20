package com.linkedin.libot.model

/**
 * @author: Alejandro Bologna
 */

class HelpState(arguments: Map[String, String]) extends InitialState(arguments)
{
  override def answer: String = help
}