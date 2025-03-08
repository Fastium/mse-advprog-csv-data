package Entity

import Utils.Attr

abstract class Business(
  name: String,
  place: Place,
  website: String,
  currency: Currency,
  description: String) extends Attr[Business]
{
  def getName: String = name
  def getPlace: Place = place
  def getWebsite: String = website
  def getCurrency: Currency = currency
  def getDescription: String = description

  def getValue: Business = this
}
