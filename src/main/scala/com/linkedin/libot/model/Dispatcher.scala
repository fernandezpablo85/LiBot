package com.linkedin.libot.model

import java.util.concurrent.{ConcurrentHashMap}
import collection.mutable.ConcurrentMap
import collection.JavaConversions._
import com.linkedin.libot.api.Login

/**
 * @author: Pablo Fernandez
 */

object Dispatcher
{
  private val STATES : ConcurrentMap[String, State] = new ConcurrentHashMap[String, State]

  val FIND_MATCHER = """find (.*)""".r
  val HELP_MATCHER = """help (.*)""".r
  val NUMBER_MATCHER = """(\d+)""".r

  def handle(arguments : Map[String, String]) =
  {
    val key = arguments.get("userkey").get
    val previousState = STATES.getOrElse(key, new InitialState(arguments))
    var newState = transition(previousState, arguments)
    newState = if (newState.requiresAuth && !Login.isAuthorized(key)) new UnauthorizedState(arguments) else newState
    STATES.put(key, newState)
    newState.answer
  }

  def transition(previousState: State,  arguments : Map[String, String]): State =
  {
    val message = arguments.get("msg").get
    println("Processing "+ message +" with previous state: " + previousState)

    previousState match
    {
      // The first matching correspond to the root level states
      case InitialState(_) | AuthorizeState(_) | SelectPeopleState(_) => message match
      {
        case FIND_MATCHER(term) => new FindPeopleState(arguments + (("findTerm", term)))
        case _ => new InitialState(arguments)
      }
      case UnauthorizedState(_) => message match
      {
        case NUMBER_MATCHER(number) => new AuthorizeState(arguments + (("verifier", number)))
        case _ => new InitialState(arguments) { override def answer = "the verifier is a number you know..." }
      }
      case FindPeopleState(_) => message match
      {
        case NUMBER_MATCHER(number) => new SelectPeopleState(arguments + (("selection", number)) )
        case FIND_MATCHER(term) => new FindPeopleState(arguments + (("findTerm", term)))
        case _ => new InitialState(arguments)
      }
      case _ => new InitialState(arguments)
    }
  }
}