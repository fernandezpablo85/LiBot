package com.linkedin.libot.model

import com.linkedin.libot.api.Login

/**
 * @author: Pablo Fernandez
 */
object InitialState extends State
{
  val HELP_MESSAGE = """help (.*)""".r
  val FIND_MESSAGE = """find (.*)""".r

  def handle (args : Map[String, String]) =
  {
    val msg = getMessage(args)

    msg match
    {
      case FIND_MESSAGE(msg) =>
      {
        val user = getUser(args)
        if (!Login.isAuthorized(user))
        {
          StateRegistry.update(user, AwaitingVerifierState)
          "You need to authorize me on this url: " + Login.getAuthorizationUrl(user)
        }
        else
        {
          StateRegistry.update(user, new SelectPeopleState(Map(1-> "Pablo", 2-> "El Bolo", 3-> "Jairo", 4-> "Eze")))
          "Found these guys for you: (1)Pablo, (2)Alejandro, (3)El Negro Jairo, (4)The Serial Killer"
        }

      }
      case HELP_MESSAGE(msg) => "DISPLAY HELP"
      case _ => UnknownMessageGenerator.next
    }
  }
}