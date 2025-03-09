package Entity

case class Country(value: String) {
  Country.add(this)
}

object Country {
  private var countryList: List[Country] = List()

  def getList: List[Country] = countryList

  private def add(country: Country): Unit = {
    if (!countryList.exists(_.value == country.value)) {
      countryList = countryList :+ country
    }
  }
}