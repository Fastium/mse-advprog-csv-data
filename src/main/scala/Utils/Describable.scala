package Utils //jpc: packages better in lowercase

//jpc: can live without curly braces
trait Describable :
  val name: String
  val description: String
  val city: String
  val country: String
  def printDescription(): Unit = //jpc: why do we need a print? isn't it better to override toString?
    println("Name: " + name)
    println("Location: " + country + ", " + city)
    println("Description: " + description)
  

