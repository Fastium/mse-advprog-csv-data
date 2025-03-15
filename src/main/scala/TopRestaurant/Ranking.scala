package TopRestaurant

import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet

case class Ranking(value: Option[Int])

object Ranking:
  private val rankingListRef: AtomicReference[ListSet[Ranking]] = new AtomicReference(ListSet.empty[Ranking])

  def getList: ListSet[Ranking] = rankingListRef.get()

  def add(ranking: Ranking): Ranking =
    val currentList = rankingListRef.get()
    if (!currentList.contains(ranking)) {
      val newList = currentList + ranking
      rankingListRef.compareAndSet(currentList, newList)
    }
    ranking

  def apply(value: Option[Int]): Ranking =
    val ranking = new Ranking(value)
    add(ranking)
    ranking