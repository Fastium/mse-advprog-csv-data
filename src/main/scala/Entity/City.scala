package Entity

import Utils.Attr

case class City(city : String) extends Attr[String] {
  def getValue: String = city
}

