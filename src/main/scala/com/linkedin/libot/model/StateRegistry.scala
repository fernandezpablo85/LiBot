package com.linkedin.libot.model

import collection.mutable.ConcurrentMap
import java.util.concurrent.ConcurrentHashMap
import collection.JavaConversions._

/**
 * @author: Pablo Fernandez
 */
object StateRegistry
{
  private val STATES : ConcurrentMap[String, State] = new ConcurrentHashMap[String, State]

  def getStateFor(userId : String) =
  {
    STATES.getOrElseUpdate(userId, InitialState)
  }

  def update(userId : String, state : State)
  {
    println("Moving user "+userId+" from "+getStateFor(userId).getClass.getSimpleName+" to "+state.getClass.getSimpleName)
    STATES.put(userId, state)
  }
}