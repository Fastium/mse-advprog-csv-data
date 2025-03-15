package Entity

import Utils.Describable
import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet

abstract class Business(
                         val name: String,
                         val place: Place,
                         val website: String,
                         val currency: Currency,
                         val description: String
                       ) extends Describable :
  val country: String = place.country.value
  val city: String = place.city.value

object Business:
  private val businessListRef: AtomicReference[ListSet[Business]] = new AtomicReference(ListSet.empty[Business])

  def getList: ListSet[Business] = businessListRef.get()

  def add(business: Business): Business =
    val currentList = businessListRef.get()
    if (!currentList.contains(business)) {
      val newList = currentList + business
      businessListRef.compareAndSet(currentList, newList)
    }
    business

