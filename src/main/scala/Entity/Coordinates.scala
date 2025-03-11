package Entity

case class Coordinates(latitude : Option[Float] , longitude: Option[Float]):
  def getLat: Option[Float] = latitude //jpc: i don't see why we need get here. just use immutable val
  def getLongitude: Option[Float] = longitude

