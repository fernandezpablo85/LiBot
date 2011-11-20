package com.linkedin.libot.util

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

/**
 * @author: Pablo Fernandez
 */
class ParamsExtractorsSpec extends Spec with ShouldMatchers
{
  describe("Parameter Extractor")
  {
    val source = "channel=private&botkey=F65B682C-77B9-408A-9803E261DAA3908C&userkey=1BA8C9B3-BEF4-45B8-9153082C0CBE0C71&user=fernandezpablo85%40gmail.com&network=gtalk&msg=didounatou&step=1&value0=didounatou&to=linkedin.bot%40gmail.com"

    it("should extract parameters from source")
    {
      val extracted = ParamsExtractor.extract(source, "user", "network")
      extracted("user") should be ("fernandezpablo85%40gmail.com")
      extracted("network") should be ("gtalk")
    }
  }
}