package com.linkedin.libot.model

/**
 * @author: Alejandro Bologna
 */
class SelectPeopleState(choices: Map[Int, String]) extends State
{
  override def handle(args : Map[String, String]) =
  {
    val choice = getMessage(args).toInt
    val user = getUser(args)
    StateRegistry.update(user, new PromptToGoBackState(this))

    "your pick was: " + choices.getOrElse(choice, "<undefined>") + ". Wanna try another? y/n"
  }
}
