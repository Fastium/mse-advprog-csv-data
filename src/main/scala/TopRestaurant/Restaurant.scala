package TopRestaurant

import Entity.*
import Utils.FilterCriteria

import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet


case class Restaurant(
                       override val name: String,
                       override val place: Place,
                       override val website: String,
                       override val currency: Currency,
                       override val description: String,
                       ranking: Ranking,
                       stars: Option[Int],
                       chef: Chef,
                       menu: Option[Int],
                       team: Team[Cook] = Team[Cook]()) extends Business(name, place, website, currency, description)





object Restaurant:
  private val restaurantListRef: AtomicReference[ListSet[Restaurant]] = new AtomicReference(ListSet.empty[Restaurant])

  def getList: ListSet[Restaurant] = restaurantListRef.get()

  def add(restaurant: Restaurant): Restaurant =
    val currentList = restaurantListRef.get()
    if (!currentList.contains(restaurant)) {
      val newList = currentList + restaurant
      restaurantListRef.compareAndSet(currentList, newList)
    }
    Business.add(restaurant)
    restaurant

  def update(oldRestaurant: Restaurant, newRestaurant: Restaurant): Unit =
    val currentList = restaurantListRef.get()
    if currentList.contains(oldRestaurant) then
      val newList = (currentList - oldRestaurant) + newRestaurant
      restaurantListRef.compareAndSet(currentList, newList)

  def apply(
             name: String,
             place: Place,
             website: String,
             currency: Currency,
             description: String,
             ranking: Ranking,
             stars: Option[Int],
             chef: Chef,
             menu: Option[Int]
           ): Restaurant =
    val restaurant = new Restaurant(name, place, website, currency, description, ranking, stars, chef, menu)
    add(restaurant)
    restaurant

  // calculate restaurant menu price with discount and number of people
  // use of lambda function, because it can totally be parallelized
  val lb_calculate_price: (Restaurant, Option[Int], Option[Int]) => Restaurant = (restaurant: Restaurant, discount: Option[Int], people: Option[Int]) =>
    restaurant.copy(menu = Option[Int](restaurant.menu.get - (restaurant.menu.get * discount.get / 100) * people.get))


enum RestaurantCriteria:
  case Country
  case City
  case Stars
  case minRanking


object RestaurantFilter extends FilterCriteria :
  type ItemType = Restaurant
  type CriteriaType = Map[RestaurantCriteria, Any]

  def matches(restaurant: ItemType, criteria: CriteriaType): Boolean =
    criteria.forall:
      case (RestaurantCriteria.Country, country) => restaurant.country == country.toString
      case (RestaurantCriteria.City, city) => restaurant.city == city.toString
      case (RestaurantCriteria.Stars, stars) => restaurant.stars == stars.toString.toIntOption
      case (RestaurantCriteria.minRanking, minRanking) => restaurant.ranking.value.get >= minRanking.toString.toIntOption.get
