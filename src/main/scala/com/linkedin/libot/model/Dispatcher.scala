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
    val state = transition2(previousState, arguments)
    state.updateArgs(arguments)

    val newState = if (state.requiresAuth && !Login.isAuthorized(key)) new UnauthorizedState(arguments) else state

    STATES.put(key, newState)
    newState.answer
  }

  def transition(previousState: State,  arguments : Map[String, String]): State =
  {
    val message = arguments.get("msg").get
    println("Processing "+message+" with previous state: " + previousState.name)

    message match
    {
      case FIND_MATCHER(_) => previousState.name match
      {
        case "InitialState" | "FindPeopleState" => new FindPeopleState(arguments)
        case _ => new InitialState(arguments)
      }
      case NUMBER_MATCHER(_) => previousState.name match
      {
        case "FindPeopleState" => new SelectPeopleState(arguments)
        case "UnauthorizedState" => new AuthorizeState(arguments)
        case _ => new InitialState(arguments)
      }
      case _ => new InitialState(arguments)
    }
  }

  def transition2(previousState: State,  arguments : Map[String, String]): State =
  {
    val message = arguments.get("msg").get
    println("Processing "+ message +" with previous state: " + previousState.name)

    previousState.name match
    {
      case "InitialState" => message match
      {
        case FIND_MATCHER(_) => new FindPeopleState(arguments)
        case _ => new InitialState(arguments)
      }
      case "UnauthorizedState" => message match
      {
        case NUMBER_MATCHER(_) => new AuthorizeState(arguments)
        case _ => new InitialState(arguments) {override def answer = "the verifier is a number you know..."}
      }
      case "FindPeopleState" => message match
      {
        case NUMBER_MATCHER(_) => new SelectPeopleState(arguments)
        case FIND_MATCHER(_) => new FindPeopleState(arguments)
        case _ => new InitialState(arguments)
      }
      case _ => new InitialState(arguments)
    }
  }
}