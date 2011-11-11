package com.linkedin.libot.model

import org.scalatest.Spec

/**
 * @author: Pablo Fernandez
 */
class DispatcherTest extends Spec
{
  describe("LiBot dispatcher")
  {
    it("should default to UnknownMessage if no other message matches the given input")
    {
      Dispatcher.handle("Are you siri?").isInstanceOf[UnknownMessage]
      Dispatcher.handle("Where's the closest ATM machine?").isInstanceOf[UnknownMessage]
      Dispatcher.handle("Do I need an umbrella today?").isInstanceOf[UnknownMessage]
    }
  }
}