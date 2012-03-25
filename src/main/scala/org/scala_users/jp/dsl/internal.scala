package org.scala_users.jp.dsl

object internal {
  
  implicit def stringToHierarchyMap(prefix: String) = new {

    def := (values: Any*): HierarchyMap[String] =
      prefix :+: values.map {
        case v: String => new HierarchyMap(Some(v), Map.empty)
        case v: HierarchyMap[String] => v
      }.reduceLeft(_ ++ _)

  }
  
}