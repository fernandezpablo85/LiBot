package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

trait State
{
  def transition (input : String) : State
  def answer: String
}