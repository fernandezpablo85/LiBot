package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

object Dispatcher
{
  private val STATES = new java.util.concurrent.ConcurrentHashMap[String, RequestState]()

  def handle(input : String, id : String) =
  {
    val state = if (STATES.get(id) != null) STATES.get(id) else new InitialState

    val (message, nextState) = state.handle(input)
    STATES.put(id, nextState)

    message
  }
}