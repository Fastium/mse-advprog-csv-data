package Entity

import Utils.Describable

abstract class Business(
  name: String,
  place: Place,
  website: String,
  currency: Currency,
  description: String) extends Describable 
{
  def getDescription: String = description
  def getName: String = name
}
