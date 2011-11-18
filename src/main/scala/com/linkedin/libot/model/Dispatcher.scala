package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

object Dispatcher
{
  private val STATES = new java.util.concurrent.ConcurrentHashMap[String, State]()

  def handle(input : String, id : String) =
  {
    val state = if (STATES.get(id) != null) STATES.get(id) else new InitialState

    val newState = state.transition(input)

    STATES.put(id, newState)

    newState.answer
  }
}