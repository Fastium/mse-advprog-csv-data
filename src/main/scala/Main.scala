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

    // filter with anonymous function
    // all restaurants with a menu price less than 200
    val restaurantPriceLessThan200 = Restaurant.getList.filter(r => r.menu.get < 200)
    println("Restaurants with a menu price less than 200: \n")
    restaurantPriceLessThan200.foreach(r => r.printDescription())

    // all restaurants with a menu price less than 200 and a ranking greater than 50
    val restaurantPriceLessThan200AndRankingGreaterThan50 = Restaurant.getList.filter(r => r.menu.get < 350 && r.ranking.value.get < 10)
    println("Restaurants with a menu price less than 350 and a ranking greater than 10: \n")
    restaurantPriceLessThan200AndRankingGreaterThan50.foreach(r => r.printDescription())


    // get coordinates with call-by-name of a swiss restaurant
    val restaurantSwitzerland = Restaurant.getList.find(r => r.country == "Switzerland")
    println("Restaurant in Switzerland with coordinates: \n")
    restaurantSwitzerland match {
      case Some(restaurant) =>
        val (latitude, longitude) = restaurant.place.coordinates.getCoordinates()
        restaurant.printDescription()
        println("Coordinates: " + latitude.get + ", " + longitude.get)
      case None =>
        println("No restaurant found matching criteria")
    }
