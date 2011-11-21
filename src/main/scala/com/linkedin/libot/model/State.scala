package com.linkedin.libot.model

abstract class State(context: Map[String, String])
{
  val user = context.get("userkey").get
  val message = context.get("msg").get

  def answer: String
  def requiresAuth: Boolean = false
}