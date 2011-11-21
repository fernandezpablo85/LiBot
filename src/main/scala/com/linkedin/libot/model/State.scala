package com.linkedin.libot.model

trait State
{
  def handle (args : Map[String, String]) : String
  def requiresAuth = false
  def getUser (args : Map[String, String]) : String = args.get("userkey").get
  def getMessage (args : Map[String, String]) : String = args.get("msg").get
}