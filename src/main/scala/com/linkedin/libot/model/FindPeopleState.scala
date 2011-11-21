package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */
class FindPeopleState(arguments : Map[String, String]) extends InitialState(arguments)
{
  override def answer = "I found some guys matching "+ message +"\n1.John Doe\n2.John McCarthy\n3.John Lennon\nYou pick"

  override def help = "Specific help about FindPeopleState"

  override def requiresAuth = true
}