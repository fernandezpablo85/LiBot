package com.linkedin.libot.model

/**
 * @author: Alejandro Bologna
 */
class SelectPeopleState(arguments: Map[String, String]) extends InitialState(arguments)
{
  override def answer: String = "I think you picked " + message
}
