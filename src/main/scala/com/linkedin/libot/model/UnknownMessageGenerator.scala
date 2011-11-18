package com.linkedin.libot.model

import util.Random

/**
 * @author: Pablo Fernandez
 */
object UnknownMessageGenerator
{
  val RESPONSES = List( "Sorry. I don't know how to do that.",
                        "Circuits malfunctioning, please try again.",
                        "Whatever you say.",
                        "Hey that sounds like a good idea... no wait. It doesn't.",
                        "Unable to process command.")

  def next = RESPONSES(Random.nextInt(UnknownMessageGenerator.RESPONSES.length))
}