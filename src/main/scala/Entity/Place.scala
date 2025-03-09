package Entity


case class Place(city: City, country: Country, coordinates: Coordinates) {
  def getCity: String = city.value
  def getCountry: String = country.value
  def getCoordinates: Coordinates = coordinates
}
