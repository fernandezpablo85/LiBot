package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

class InitialState(args : Map[String, String]) extends State
{
  var arguments = args
  def message = arguments.get("msg").get
  def user = arguments.get("userkey").get

  def answer: String = UnknownMessageGenerator.next

  def help = "Need some help on " + message + " huh? Try with 'find John'\nIt's all I have for now..."
  def getHelpState : State = new HelpState(arguments)

  def transition =
  {
    println("Processing "+message+" ...")
    message match
    {
      case InitialState.HELP_MATCHER(term) => getHelpState
      case InitialState.FIND_MATCHER(name) => new FindPeopleState(arguments)
      case _ => new InitialState(arguments)
    }
  }

  def updateArgs(args : Map[String, String]) = arguments = args
}

object InitialState
{
  val FIND_MATCHER = """find (.*)""".r
  val HELP_MATCHER = """help (.*)""".r
}