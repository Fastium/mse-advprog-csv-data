package Entity

import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet

case class City(value : String)

object City :
  private val cityListRef: AtomicReference[ListSet[City]] = new AtomicReference(ListSet.empty[City])

  def getList: ListSet[City] = cityListRef.get()

  def add(city: City): City =
    val currentList = cityListRef.get()
    if (!currentList.exists(_.value == city.value)) {
      val newList = currentList + city
      cityListRef.compareAndSet(currentList, newList)
    }
    city

  def apply(value: String): City =
    val city = new City(value)
    add(city)
    city