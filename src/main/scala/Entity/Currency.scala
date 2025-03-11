package Entity

case class Currency(value: String) :
  Currency.add(this)  // jpc: same problem here as in the other classes


object Currency :
  //jpc: get rid of your mutable state
  private var currencyList: List[Currency] = List()

  def getList: List[Currency] = currencyList

  private def add(currency: Currency): Unit = 
    if (!currencyList.exists(_.value == currency.value)) {
      currencyList = currencyList :+ currency
    }
  
