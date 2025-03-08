package TopRestaurant

import Utils.Attr

case class Ranking(ranking: Option[Int]) extends Attr[Option[Int]] {
  def getValue: Option[Int] = ranking
}
