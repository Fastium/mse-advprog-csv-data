package Entity

import Utils.Attr

case class Currency(currency: String) extends Attr[String] {
  def getValue: String = currency
}
