package com.linkedin.libot.model

/**
 * @author: Pablo Fernandez
 */

trait State
{
  def name: String
  def answer: String
  def updateArgs (args : Map[String, String]) : Unit
  def requiresAuth: Boolean
}