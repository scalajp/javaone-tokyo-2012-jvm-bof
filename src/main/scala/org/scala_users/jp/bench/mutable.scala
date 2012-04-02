package org.scala_users.jp.bench.mutable

import org.scala_users.jp.bench.RBTreeMapI

sealed abstract class RBTree {
  import RBTree._

  var color: Int
  var left: RBTree
  var right: RBTree

  def get(key: String): String
  def put(key: String, value: String): RBTree = {
    val result = insert(key, value)
    result.color = Black
    result
  }
  private[bench] def insert(key: String, value: String): RBTree
  def height: Int
}
object RBTree {
  val Black = 1
  val Red = 0

  def rotateLeft(node: RBTree): RBTree = {
    val r = node.right
    node.right = r.left
    r.left = node
    r.color = r.left.color
    r.left.color = Red
    r
  }

  def rotateRight(node: RBTree): RBTree = {
    val l = node.left
    node.left = l.right
    l.right = node
    l.color = node.right.color
    l.right.color = Red
    l
  }

  def flipColors(node: RBTree) {
    node.color = node.color ^ 1
    node.left.color = node.left.color ^ 1
    node.right.color = node.right.color ^ 1
  }

}
object Empty extends RBTree {
  import RBTree._

  var color = Black
  var left: RBTree = null
  var right: RBTree = null

  def get(key: String): String = null
  def insert(key: String, value: String) = new Node(Red, key, value, this, this)
  val height = 0
}
class Node(var color: Int, var key: String, var value: String, var left: RBTree, var right: RBTree) extends RBTree {
  import RBTree._

  def get(k: String) = {
    val c = k compare key
    if      (c < 0) left.get(k)
    else if (c > 0) right.get(k)
    else            value
  }

  def insert(k: String, v: String) = {
    val c = k compare key
    if      (c < 0) left = left.insert(k, v)
    else if (c > 0) right = right.insert(k, v)
    else            value = v

    var node: RBTree = this
    if (node.left.color == Black && node.right.color == Red) node = rotateLeft(node)
    if (node.left.color == Red && node.left.left.color == Red) node = rotateRight(node)
    if (node.left.color == Red && node.right.color == Red) flipColors(node)
    node
  }

  def height = left.height + color
}

class RBTreeMap extends RBTreeMapI {
  var root: RBTree = Empty

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
}
object RBTreeMap {
  def apply(entries: (String, String)*): RBTreeMap = entries.foldLeft(new RBTreeMap) {
    case (map, (key, value)) => map.put(key, value)
  }
  def newInstance: RBTreeMap = new RBTreeMap
}
