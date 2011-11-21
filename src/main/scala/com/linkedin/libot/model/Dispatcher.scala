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
  def handle(args: Map[String, String]) =
  {
    val key = args.get("userkey").get
    val userState = StateRegistry.getStateFor(key)

    userState.handle(args)
  }
}