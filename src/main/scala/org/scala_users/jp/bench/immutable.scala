package org.scala_users.jp.bench.immutable

sealed abstract class Color(val height: Int)
case object Red extends Color(0)
case object Black extends Color(1)

case class Entry(key: String, value: String)

sealed abstract class RBTree {
  def get(key: String): String
  def put(key: String, value: String): RBTree = ins(key, value).copy(color = Black)
  private[bench] def ins(key: String, value: String): Node
  def height: Int
}
case object Empty extends RBTree {
  def get(key: String) = null

  private[bench] def ins(key: String, value: String) =
    Node(Red, Empty, Entry(key, value), Empty)

  val height = 0
}
case class Node(color: Color, left: RBTree, entry: Entry, right: RBTree) extends RBTree {
  def get(key: String) = key compare entry.key match {
    case c if c < 0 => left.get(key)
    case c if c > 0 => right.get(key)
    case _          => entry.value
  }

  private[bench] def ins(key: String, value: String) = key compare entry.key match {
    case c if c < 0 => balance(color, left.ins(key, value), entry, right)
    case c if c > 0 => balance(color, left, entry, right.ins(key, value))
    case _          => Node(color, left, Entry(key, value), right)
  }

  import org.scala_users.jp.bench.immutable.{Node => N, Red => R, Black => B}
  private val balance: (Color, RBTree, Entry, RBTree) => Node = {
    case (B, N(R, N(R, a, x, b), y, c), z, d) => N(R, N(B, a, x, b), y, N(B, c, z, d))
    case (B, N(R, a, x, N(R, b, y, c)), z, d) => N(R, N(B, a, x, b), y, N(B, c, z, d))
    case (B, a, x, N(R, N(R, b, y, c), z, d)) => N(R, N(B, a, x, b), y, N(B, c, z, d))
    case (B, a, x, N(R, b, y, N(R, c, z, d))) => N(R, N(B, a, x, b), y, N(B, c, z, d))
    case (c, l, e, r) => N(c, l, e, r)
  }
  
  lazy val height = color.height + (left.height max right.height)
}

class RBTreeMap {
  private var root: RBTree = Empty

  def throwExIfNull(key: String) {
    if (key == null) throw new IllegalArgumentException("key is null")
  }
  
  def get(key: String): String = {
    throwExIfNull(key)
    root.get(key)
  }
  
  def put(key: String, value: String): RBTreeMap = {
    throwExIfNull(key)
    root = root.put(key, value)
    this
  }
  
  def height: Int = root.height

  override def toString = root.toString
}
object RBTreeMap {
  def apply(entries: (String, String)*): RBTreeMap = entries.foldLeft(new RBTreeMap) {
    case (map, (key, value)) => map.put(key, value)
  }
  def newInstance: RBTreeMap = new RBTreeMap
}
