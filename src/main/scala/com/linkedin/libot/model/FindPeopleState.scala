package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */
class FindPeopleState(arguments : Map[String, String]) extends InitialState(arguments) with OAuthAuthorization
{
  val numberMatcher = """(\d+)""".r

  override def answer = "I found some guys matching "+ message +"\n1.John Doe\n2.John McCarthy\n3.John Lennon\nYou pick"

  override def transition =
  {
    message match
    {
      case numberMatcher(number) => new SelectPeopleState(arguments)
      case _ => new InitialState(arguments)
    }
  }

  override def help = "Specific help about FindPeopleState"
}
