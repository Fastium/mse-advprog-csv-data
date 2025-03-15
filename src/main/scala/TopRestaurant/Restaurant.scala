package TopRestaurant

import Entity.*
import Utils.Describable
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
                       chef: String,
                       menu: Option[Int]
                     ) extends Business(name, place, website, currency, description)

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

  def apply(
             name: String,
             place: Place,
             website: String,
             currency: Currency,
             description: String,
             ranking: Ranking,
             stars: Option[Int],
             chef: String,
             menu: Option[Int]
           ): Restaurant =
    val restaurant = new Restaurant(name, place, website, currency, description, ranking, stars, chef, menu)
    add(restaurant)
    restaurant