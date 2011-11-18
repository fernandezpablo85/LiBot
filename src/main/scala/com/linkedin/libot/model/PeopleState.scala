package com.linkedin.libot.model

/**
 * @author: Alejandro Bologna
 */

class FindPeopleState(name: String) extends InitialState
{
  override def answer: String =
  {
    "I found some guys matching " + name + "\n1.John Doe\n2.John McCarthy\n3.John Lennon\nYou pick"
  }

  override def transition (input: String) =
  {
    val numberMatcher = """(\d+)""".r
    
    input match
    {
      case numberMatcher(number) => new SelectPeopleState(number)
      case _ => super.transition(input)
    }
  }

  override def getHelpState(term: String) : State =
  {
    new FindPeopleState(name) { override def answer = "Specific help about FindPeopleState" }
  }
}

class SelectPeopleState(selected: String) extends InitialState
{
  override def answer: String =
  {
    "I think you picked " + selected
  }

  override def transition (input: String) =
  {
    input match
    {
      case _ => super.transition(input)
    }
  }
}
