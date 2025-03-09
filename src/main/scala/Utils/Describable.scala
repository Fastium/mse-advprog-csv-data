package Utils

trait Describable {
  def getDescription: String
  def getName: String
  def printDescription(): Unit = {
    println(getName)
    println(getDescription)
  }
}
