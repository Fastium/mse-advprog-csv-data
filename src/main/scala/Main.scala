import TopRestaurant.*
import Entity.*

import com.github.tototoshi.csv.*
import java.io.File
import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet

@main def run_application() : Unit=
    val reader = CSVReader.open(new File("src/main/resources/02-50BestRestaurants.csv"))
    val it = reader.iterator

    val col_names = it.next()
    val colNamesMap: Map[String, Int] = col_names.zipWithIndex.toMap

    val restaurants = AtomicReference[ListSet[Restaurant]]

    while it.hasNext do
      val line = it.next()

      val ranking = Ranking(line(colNamesMap("Ranking")).toIntOption)
      val restaurant = line(colNamesMap("Restaurant"))
      val city = City(line(colNamesMap("City")))
      val country = Country(line(colNamesMap("Country")))
      val coordinates = Coordinates(line(colNamesMap("Lat")).toFloatOption, line(colNamesMap("Lon")).toFloatOption)
      val Stars = line(colNamesMap("Stars")).toIntOption
      val chef = Chef(line(colNamesMap("Chef")))
      val website = line(colNamesMap("Website"))
      val menu = line(colNamesMap("Menu")).toIntOption
      val currency = Currency(line(colNamesMap("Currency")))
      val description = line(colNamesMap("Description"))

      Restaurant(restaurant, Place(city, country, coordinates), website, currency, description, ranking, Stars, chef, menu)

    reader.close()

//    for(b <- Business.getList) do
//      b.printDescription()
//      println()



    // Filter by country
    println("Restaurants in Italy: \n")
    val criteria = Map(RestaurantCriteria.Country -> "Italy")
    for (r <- Restaurant.getList) do
      if RestaurantFilter.matches(r, criteria) then
        r.printDescription()
        println()

    // Filter by city and country
    println("Restaurants in Axpe, Spain: \n")
    val criteria2 = Map(RestaurantCriteria.City -> "Axpe", RestaurantCriteria.Country -> "Spain")
    val r_spain_axpe: Option[Restaurant] = Restaurant.getList.find(r =>
      RestaurantFilter.matches(r, criteria2)
    )

    // Build team to use
    r_spain_axpe match {
      case Some(restaurant) =>
        restaurant.printDescription()
        println()
        val r_spain_axpe_upd = restaurant.copy(
          team = restaurant.team.build(List(
            Cook("Mikel Urrutia"),
            Cook("Iker Etxeberria"),
            Cook("Ainhoa Goikoetxea"),
            restaurant.chef
          ))
        )
        Restaurant.update(restaurant, r_spain_axpe_upd)
        r_spain_axpe_upd.team.printMembers()
      case None =>
        println("No restaurant found matching criteria")
    }

    // calculation of discount on all restaurants
    println("Price on all restaurants: \n")
    val discount = Option[Int](10)
    val numberPeople = Option[Int](4)
    // use the map function to calculate the price of each restaurant with a lambda function to parallelize the calculation
    val newRestaurantsPrices = Restaurant.getList.map(r =>
      Restaurant.lb_calculate_price(r, discount, numberPeople)
    )
    for (r <- newRestaurantsPrices) do
      r.printDescription()
      println("Price: " + r.menu.get + " " + r.currency.value + " (discount: " + discount.get + "%, people: " + numberPeople.get + ")")
      println()




