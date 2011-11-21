package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

case class InitialState(context: Map[String, String]) extends State(context)
{
  def answer: String = UnknownMessageGenerator.next
}