package TopRestaurant

import Entity.*

case class Restaurant(
  name: String,
  place: Place,
  website: String,
  currency: Currency,
  description: String,
  ranking: Ranking,
  stars: Option[Int],
  chef: String,
  menu: Option[Int]) extends Business( name, place, website, currency, description) {
  def getRanking: Option[Int] = ranking.getValue
  def getStars: Option[Int] = stars
  def getChef: String = chef
  def getMenu: Option[Int] = menu
}

