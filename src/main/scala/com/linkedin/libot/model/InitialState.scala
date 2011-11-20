package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

class InitialState(arguments : Map[String, String]) extends State
{
  val message = arguments.get("msg").get
  val user = arguments.get("userkey").get

  def answer: String = UnknownMessageGenerator.next

  def help = "Need some help on " + message + " huh? Try with 'find John'\nIt's all I have for now..."
  def getHelpState : State = new HelpState(arguments)

  def transition =
  {
    val FIND_MATCHER = "find (.*)".r
    val HELP_MATCHER = "help (.*)".r

    message match
    {
      case HELP_MATCHER(term) => getHelpState
      case FIND_MATCHER(name) => new FindPeopleState(arguments)
      case _ => new InitialState(arguments)
    }
  }
}