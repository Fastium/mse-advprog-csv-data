package Entity


case class Place(city: City, country: Country, coordinates: Coordinates) :
  def getCity: String = city.value  //jpc: again the "get" methods here make no much sense
  def getCountry: String = country.value
  def getCoordinates: Coordinates = coordinates

