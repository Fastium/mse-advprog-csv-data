package Entity

import Utils.Attr

case class Country(country: String) extends Attr[String] {
  def getValue: String = country
}
