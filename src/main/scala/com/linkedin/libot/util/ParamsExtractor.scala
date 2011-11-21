package com.linkedin.libot.util

/**
 * @author: Pablo Fernandez
 */
object ParamsExtractor
{
  val NAME_VALUE_REGEX = """(.*)=(.*)""".r

  def extract(source : String, argNames : String*) =
  {
    require(source != null && source.length > 0, "source must be a non-empty string")
    println("Extracting parameters from: '"+source+"'")

    def shouldYield(name : String) = if (argNames.isEmpty) true else argNames.map(_.toLowerCase).contains(name.toLowerCase)

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