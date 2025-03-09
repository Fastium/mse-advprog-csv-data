import TopRestaurant.*
import Entity.*

import com.github.tototoshi.csv.*
import java.io.File



object Main {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("src/main/resources/02-50BestRestaurants.csv"))
    val it = reader.iterator

    val col_names = it.next()
    val colNamesMap: Map[String, Int] = col_names.zipWithIndex.toMap

    var restaurants = List[Restaurant]()


    while (it.hasNext) {
      val line = it.next()

      val ranking = Ranking(line(colNamesMap("Ranking")).toIntOption)
      val restaurant = line(colNamesMap("Restaurant"))
      val city = City(line(colNamesMap("City")))
      val country = Country(line(colNamesMap("Country")))
      val coordinates = Coordinates(line(colNamesMap("Lat")).toFloatOption, line(colNamesMap("Lon")).toFloatOption)
      val Stars = line(colNamesMap("Stars")).toIntOption
      val chef = line(colNamesMap("Chef"))
      val website = line(colNamesMap("Website"))
      val menu = line(colNamesMap("Menu")).toIntOption
      val currency = Currency(line(colNamesMap("Currency")))
      val description = line(colNamesMap("Description"))


      restaurants = restaurants :+ Restaurant(restaurant, Place(city, country, coordinates), website, currency, description, ranking, Stars, chef, menu)


    }
    reader.close()

    for(restaurant <- restaurants){
      restaurant.printDescription()
      println()
    }

  }

}