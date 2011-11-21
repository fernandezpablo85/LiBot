package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */
case class FindPeopleState(context : Map[String, String]) extends State(context)
{
  val term = context.get("findTerm").get
  override def answer = "I found some guys matching "+ term +"\n1.John Doe\n2.John McCarthy\n3.John Lennon\nPick one"
  override def requiresAuth = true
}