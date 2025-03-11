package Entity

case class Country(value: String) :
  Country.add(this)


object Country :
  private var countryList: List[Country] = List() // jpc: better not use var

  def getList: List[Country] = countryList

  // jpc: Unit return values means you are doing side effects and this is not a good practice. I would refrain from using mutable collections in this type of scenarios
  private def add(country: Country): Unit = 
    if (!countryList.exists(_.value == country.value)) {
      countryList = countryList :+ country
    }
  
