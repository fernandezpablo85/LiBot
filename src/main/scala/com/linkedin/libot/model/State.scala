package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

trait State
{
  def transition : State
  def answer: String
}