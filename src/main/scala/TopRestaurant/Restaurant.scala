package TopRestaurant

import Entity.*


case class Restaurant(
  name: String,  place: Place,  website: String,  currency: Currency,  description: String,
  ranking: Ranking,  stars: Option[Int],  chef: String,  menu: Option[Int])
  extends Business( name, place, website, currency, description)
{
}

