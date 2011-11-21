package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */
class PromptToGoBackState(oldState: State) extends State
{
  def handle (args : Map[String, String]) =
  {
    val msg = getMessage(args)
    val user = getUser(args)

    msg.toLowerCase match {
      case "yes" =>
      {
        StateRegistry.update(user, oldState)
        "Cool, pick another one then: "
      }
      case "no" =>
      {
        StateRegistry.update(user, InitialState)
        "Ok. What's your next command, master?"
      }
      case _ => "Please say 'yes' or 'no'"
    }
  }
}