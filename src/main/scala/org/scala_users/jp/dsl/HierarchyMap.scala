package org.scala_users.jp.dsl

import collection.immutable.MapLike
import collection.generic.{CanBuildFrom}
import collection.mutable.{MapBuilder, Builder}
import java.util.Properties

@SerialVersionUID(1L)
class HierarchyMap[+B] private[dsl] (value: Option[B], suffixes: Map[String, HierarchyMap[B]])
    extends Map[String, B]
    with MapLike[String, B, HierarchyMap[B]]
    with Serializable {

  override def empty = HierarchyMap.empty

  import HierarchyMap.separate

  def + [B1 >: B](kv: (String, B1)): HierarchyMap[B1] = this + (separate(kv._1), kv._2)

  private[dsl] def + [B1 >: B](k: List[String], v: B1): HierarchyMap[B1] = k match {
    case Nil => new HierarchyMap(Some(v), suffixes)
    case head :: tail =>
      val sub = suffixes.get(head).getOrElse(empty)
      new HierarchyMap(value, suffixes + ((head, sub + (tail, v))))
  }

  def - (key: String): HierarchyMap[B] = this - separate(key)

  private[dsl] def - (key: List[String]): HierarchyMap[B] = key match {
    case Nil => new HierarchyMap(None, suffixes)
    case head :: tail => suffixes.get(head).map { m =>
      val subSuffixes = m - tail
      if (subSuffixes.isEmpty) new HierarchyMap(value, suffixes - head)
      else if (subSuffixes == m) this
      else new HierarchyMap(value, suffixes + ((head, subSuffixes)))
    }.getOrElse(this)
  }

  def get(key: String): Option[B] = get(separate(key))

  private[dsl] def get(key: List[String]): Option[B] = key match {
    case Nil => value
    case head :: tail => suffixes.get(head).flatMap { _.get(tail) }
  }

  def iterator: Iterator[(String, B)] = itr.map {
    case (k, v) => (k.mkString("."), v)
  }

  private[dsl] def itr: Iterator[(List[String], B)] =
    (for (v <- value.iterator) yield (Nil, v)) ++
    (for {
      (suffix, map) <- suffixes.iterator
      (key, value) <- map.itr
    } yield (suffix :: key, value))

  override def isEmpty: Boolean = value.isEmpty && suffixes.isEmpty

  def toProperties(implicit ev: B <:< String): Properties = iterator.foldLeft(new Properties) {
    case (p, (k, v)) => p.setProperty(k, v); p
  }

  def :+: (prefix: String): HierarchyMap[B] = separate(prefix) ::+:: this

  private[dsl] def ::+:: (prefix: String): HierarchyMap[B] = {
    assert(prefix.forall { '.' != })
    new HierarchyMap(None, Map(prefix -> this))
  }

  private[dsl] def ::+:: (prefixes: List[String]): HierarchyMap[B] = prefixes.foldRight(this) {
    case (prefix, map) => prefix ::+:: map
  }
  
  override def toString = "HierarchyMap(%s, %s)".format(value, suffixes)
}


object HierarchyMap {

  val e: HierarchyMap[Nothing] = new HierarchyMap(None, Map.empty)

  def empty[B]: HierarchyMap[B] = e

  def apply[B](kvs: (String, B)*): HierarchyMap[B] = kvs.foldLeft(empty[B]) {
    case (map, pair) => map + pair
  }
  
  def newBuilder[B]: Builder[(String, B), HierarchyMap[B]] =
    new MapBuilder[String, B, HierarchyMap[B]](empty)

  implicit def canBuildFrom[B] = new CanBuildFrom[HierarchyMap[_], (String, B), HierarchyMap[B]] {
    def apply(from: HierarchyMap[_]): Builder[(String, B), HierarchyMap[B]] = apply()
    def apply(): Builder[(String, B), HierarchyMap[B]] = newBuilder[B]
  }

  private[dsl] def separate(key: String): List[String] = key.split('.').toList

}
