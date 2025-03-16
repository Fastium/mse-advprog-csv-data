package Entity

import java.util.concurrent.atomic.AtomicReference
import scala.collection.immutable.ListSet

case class Team[+T<:Person](members: List[T] = List.empty[T]):

  def build[U >: T <: Person](team: List[U]): Team[U] =
    team.foldLeft(this.asInstanceOf[Team[U]])((acc, member) => acc.addMember(member))

  private def addMember[U >: T <: Person](newMember: U): Team[U] =
    Team[U](newMember :: members)

  def printMembers(): Unit =
    if members.isEmpty then
      println("No members")
    else
      println("Members: ")
      members.foreach(m => println(m.name))
