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

  def handle(arguments : Map[String, String]) =
  {
    val key = arguments.get("userkey").get
    val state = STATES.getOrElse(key, new InitialState(arguments))
    state.updateArgs(arguments)
    val newState = state.transition
    STATES.put(key, newState)
    newState.answer
  }
}