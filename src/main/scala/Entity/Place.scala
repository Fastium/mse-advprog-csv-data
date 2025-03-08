package Entity


case class Place(city: City, country: Country, coordinates: Coordinates) {
  def getCity: String = city.getValue
  def getCountry: String = country.getValue
  def getCoordinates: Coordinates = coordinates
}
