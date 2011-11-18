package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

object Dispatcher
{
  private val STATES = new java.util.concurrent.ConcurrentHashMap[String, State]()

  def handle(input : String, id : String) =
  {
    val state = if (STATES.get(id) != null) STATES.get(id) else InitialState

    STATES.put(id, state.transition(input))

    state.answer(input)
  }
}