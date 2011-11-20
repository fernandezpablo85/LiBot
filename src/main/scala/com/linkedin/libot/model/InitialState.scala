package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

class InitialState extends State
{
  def answer: String = UnknownMessageGenerator.next

  def transition (input: String) =
  {
    val FIND_MATCHER = """find (.*)""".r
    val HELP_MATCHER = """help (.*)""".r

    input match
    {
      case HELP_MATCHER(term) => getHelpState(term)
      case FIND_MATCHER(name) => new FindPeopleState(name)
      case _ => new InitialState
    }
  }

  def getHelpState(term: String) : State = new HelpState(term)
}