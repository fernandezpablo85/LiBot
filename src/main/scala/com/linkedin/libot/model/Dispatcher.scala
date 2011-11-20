package com.linkedin.libot.model

import java.util.concurrent.{ConcurrentHashMap}
import collection.mutable.ConcurrentMap
import collection.JavaConversions._

/**
 * @author: Pablo Fernandez
 */

object Dispatcher
{
  private val STATES : ConcurrentMap[String, State] = new ConcurrentHashMap[String, State]

  def handle(input : String, id : String) =
  {
    val state = STATES.getOrElse(id, new InitialState)
    val newState = state.transition(input)
    STATES.put(id, newState)
    newState.answer
  }
}