package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

class InitialState(args : Map[String, String]) extends State
{
  var arguments = args
  def message = arguments.get("msg").get
  def user = arguments.get("userkey").get

  def name = this.getClass.getSimpleName
  def requiresAuth = false

  def answer: String = UnknownMessageGenerator.next

  def help = "Need some help on " + message + " huh? Try with 'find John'\nIt's all I have for now..."
  def getHelpState : State = new HelpState(arguments)

  def updateArgs(args : Map[String, String]) = arguments = args
}