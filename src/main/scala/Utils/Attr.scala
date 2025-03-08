package Utils

trait Attr[T] {
  def getValue: T
    Attr.addAttr(this)
}

object Attr {
  private var attrList: List[Attr[_]] = List()

  def getAttrList: List[Attr[_]] = attrList

  private def existAttr(value: Any): Boolean = {
    attrList.exists(_.getValue == value)
  }

  private def addAttr(attr: Attr[_]): Unit = {
    if (!existAttr(attr.getValue)) {
      attrList = attrList :+ attr
    }
  }
}
