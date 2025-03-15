package Entity

import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet

case class Currency(value: String)

object Currency:
  private val currencyListRef: AtomicReference[ListSet[Currency]] = new AtomicReference(ListSet.empty[Currency])

  def getList: ListSet[Currency] = currencyListRef.get()

  def add(currency: Currency): Currency =
    val currentList = currencyListRef.get()
    if (!currentList.exists(_.value == currency.value)) {
      val newList = currentList + currency
      currencyListRef.compareAndSet(currentList, newList)
    }
    currency

  def apply(value: String): Currency =
    val currency = new Currency(value)
    add(currency)
    currency