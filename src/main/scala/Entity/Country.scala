package Entity

import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet

case class Country(value: String)

object Country:
  private val countryListRef: AtomicReference[ListSet[Country]] = new AtomicReference(ListSet.empty[Country])

  def getList: ListSet[Country] = countryListRef.get()

  def add(country: Country): Country =
    val currentList = countryListRef.get()
    if (!currentList.exists(_.value == country.value)) {
      val newList = currentList + country
      countryListRef.compareAndSet(currentList, newList)
    }
    country

  def apply(value: String): Country =
    val country = new Country(value)
    add(country)
    country