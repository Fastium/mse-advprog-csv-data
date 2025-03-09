package Entity

case class City(value : String){
  City.add(this)
}

object City {
  private var cityList: List[City] = List()

  def getList: List[City] = cityList

  private def add(city: City): Unit = {
    if (!cityList.exists(_.value == city.value)) {
      cityList = cityList :+ city
    }
  }
}

