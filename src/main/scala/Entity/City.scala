package Entity

case class City(value : String):
  City.add(this) //jpc: I would avoid modifying mutable state here


object City :
  // jpc: don't use mutable collections, not a good practice in general
  private var cityList: List[City] = List()
  
  def getList: List[City] = cityList

  private def add(city: City): Unit = 
    if (!cityList.exists(_.value == city.value)) {
      cityList = cityList :+ city
    }
  

