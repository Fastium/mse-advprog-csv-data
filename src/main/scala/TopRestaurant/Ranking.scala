package TopRestaurant

case class Ranking(value: Option[Int]) :
  Ranking.add(this)

object Ranking :
  //jpc: mutable state strikes again .....
  private var rankingList: List[Ranking] = List()

  def getList: List[Ranking] = rankingList

  private def add(ranking: Ranking): Unit = 
    if (!rankingList.exists(_.value == ranking.value)) then
      rankingList = rankingList :+ ranking
    
  
