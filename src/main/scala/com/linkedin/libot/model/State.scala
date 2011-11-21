package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

trait State
{
  def transition : State;
  def answer: String
  def updateArgs (args : Map[String, String]) : Unit
}