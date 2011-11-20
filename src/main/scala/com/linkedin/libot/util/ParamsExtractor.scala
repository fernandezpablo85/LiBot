package com.linkedin.libot.util

/**
 * @author: Pablo Fernandez
 */
object ParamsExtractor
{
  private val NAME_VALUE_REGEX = "(.*)=(.*)".r

  def extract(source : String, argNames : String*) =
  {
    def shouldYield(name : String) = argNames.map(_.toLowerCase).contains(name.toLowerCase)

    val arguments = source.split("&").toList
    
    val result =
      for {
        arg <- arguments
        val NAME_VALUE_REGEX(name, value) = arg
        if shouldYield(name)
      } yield name -> value

    result.toMap
  }
}