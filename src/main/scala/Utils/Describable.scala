package Utils //jpc: packages better in lowercase

//jpc: can live without curly braces
trait Describable :
  def getDescription: String  //jpc: why "get" description? can we just have a val description?
  def getName: String
  def printDescription(): Unit = //jpc: why do we need a print? isn't it better to override toString?
    println(getName)
    println(getDescription)
  

