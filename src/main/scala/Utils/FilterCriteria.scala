package Utils

trait FilterCriteria :
  type ItemType
  type CriteriaType

  def matches(item: ItemType, criteria: CriteriaType): Boolean


