package org.scala_users.jp.dsl


object internal {
  
  implicit def stringToHierarchyMap(prefix: String) = new {

    def := (value: String): HierarchyMap[String] =
      HierarchyMap.empty + (prefix -> value)

    def := (value: HierarchyMap[String]*): HierarchyMap[String] =
      prefix :+: value.reduceLeft(_ ++ _)

  }
  
}