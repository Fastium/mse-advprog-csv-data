package Entity

import Utils.Describable

//jpc: why sing an abstract class? seems better to use a trait instead
abstract class Business(
  name: String,
  place: Place,
  website: String,
  currency: Currency,
  description: String) extends Describable :

  def getDescription: String = description  // jpc: I don't see why this is working as a get method. better to use jsut a val description instead
  def getName: String = name

