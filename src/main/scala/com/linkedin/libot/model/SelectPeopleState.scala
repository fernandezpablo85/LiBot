package com.linkedin.libot.model

/**
 * @author: Alejandro Bologna
 */
case class SelectPeopleState(context: Map[String, String]) extends State(context)
{
  override def answer = "I think you picked " + message
}
