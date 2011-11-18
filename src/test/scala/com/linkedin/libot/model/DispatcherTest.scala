package com.linkedin.libot.model

import org.scalatest.Spec

/**
 * @author: Pablo Fernandez
 */
class DispatcherTest extends Spec
{
  describe("LiBot dispatcher")
  {
    it("should return unknown message if no other message matches the given input")
    {
      Dispatcher.handle("Are you siri?").isInstanceOf[UnknownMessageGenerator]
      Dispatcher.handle("Where's the closest ATM machine?").isInstanceOf[UnknownMessageGenerator]
      Dispatcher.handle("Do I need an umbrella today?").isInstanceOf[UnknownMessageGenerator]
    }
  }
}