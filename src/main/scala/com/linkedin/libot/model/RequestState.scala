package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

trait RequestState
{
  def handle (input : String) : (String, RequestState)
}