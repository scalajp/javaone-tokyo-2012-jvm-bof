JavaOne Tokyo 2012 JVM言語BoF コーディング大会 ScalaJP
======================================================

環境
-----------------------

- Scala 2.9.1
- JavaSE 7u3
- sbt 0.11.2

build 方法
-----------------------

1. [Scala 開発環境構築手順](https://github.com/scalajp/scalajp.github.com/wiki/scala-develop-environment "Scala 開発環境構築手順") 
   を参照し、sbt インストールを行います。
1. インストールした sbt の実行シェル(Mac, Linux なら sbt、Windowsなら sbt.bat) を開き、JVMオプションを以下に設定します。
   `java -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m`
1. Terminal、もしくはコマンドプロンプトにて、プロジェクトルートに移動し、`sbt assembly` を実行します。

実行方法
-----------------------

### Benchmark

1. `java -Xmx1536M -Xss1M -XX:MaxPermSize=256m -cp target/javaone-tokyo-2012-jvm-bof-assembly-0.1.jar org.scala_users.jp.bench.Benchmark`
    - Windows の場合は path 区切り文字をバックスラッシュにして下さい。 `target\javaone-tokyo-2012-jvm-bof-assembly-0.1.jar`
    - 32bit OS の場合は `-Xmx1024M` にして下さい。

### DSL

作成中

