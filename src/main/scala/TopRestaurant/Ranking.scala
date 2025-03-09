package TopRestaurant

case class Ranking(value: Option[Int]) {
  Ranking.add(this)
}

object Ranking {
  private var rankingList: List[Ranking] = List()

  def getList: List[Ranking] = rankingList

  private def add(ranking: Ranking): Unit = {
    if (!rankingList.exists(_.value == ranking.value)) {
      rankingList = rankingList :+ ranking
    }
  }
}