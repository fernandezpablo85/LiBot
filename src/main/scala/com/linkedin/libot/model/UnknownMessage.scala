package com.linkedin.libot.model

import util.Random

/**
 * @author: Pablo Fernandez
 */

case class UnknownMessage(val input : String) extends Message
{
  override def toString = UnknownMessage.RESPONSES(Random.nextInt(UnknownMessage.RESPONSES.length));
}

object UnknownMessage
{
  val RESPONSES = List( "Sorry. I don't know how to do that.",
                        "Circuits malfunctioning, please try again.",
                        "Whatever you say.",
                        "Hey that sounds like a good idea... no wait. It doesn't.",
                        "Unable to process command.")

}