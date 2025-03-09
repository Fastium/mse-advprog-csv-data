package Entity

case class Coordinates(latitude : Option[Float] , longitude: Option[Float]) {
  def getLat: Option[Float] = latitude
  def getLongitude: Option[Float] = longitude

}
