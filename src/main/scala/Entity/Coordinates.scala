package Entity

case class Coordinates(latitude : Option[Float] , longitude: Option[Float]):
  def getCoordinates(): (Option[Float], Option[Float]) = (this.latitude, this.longitude)