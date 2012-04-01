package org.scala_users.jp.bench

trait RBTreeMapI {
  def get(key: String): String
  def put(key: String, value: String): RBTreeMapI
  def height: Int
}
