# テーマ：階層化Javaプロパティファイル DSL作成

## 【概要】

Groovy, Scala, JRubyの各言語で、下記に述べるような階層化Javaプロパティファイル
DSLを作成し、実装コードの長さ、DSLとしての利便性などを比較する。

Javaのプロパティファイルは、

    key=value
    ...

という、key=valueがフラットに並んだシンプルなもので、理解しやすい。しかし、実際にプロパティファイルが活用される場面では、
keyはa.b.c.dのように.で区切られた形式になっている事がほとんどである。この.区切りの形式は、

    x.y.z.a=
    x.y.z.b=
    x.y.z.c=

のように、プレフィクスの部分(x.y.z)が共通であることがしばしばある。そのような場合、ユーザの
意図としては、プロパティのキーに階層構造を持たせたいと思われるが、プロパティファイルが
本質的にフラットである事から、その事が表現できていない。

そこで、上記と同じものを階層的に表現できるDSLを作ることを考える。

    compiler.error.message.varNotFound=...
    compiler.error.message.incompatibleType=...
    compiler.error.maxReport=
    compiler.files.input.encoding=
    compiler.files.output.encoding=

というのを、

    compiler {
     error {
       message {
         varNotFound=
         incompatibleType=
       }
     }
     maxReport=
     files {
       input.encoding=
       output.encoding=
     }
    }

のように擬似的に階層的に書けると、より読みやすくなることが考えられる。このような、
プロパティファイルのキーを階層構造で記述できるDSLを作成する事とする。

## 【要件】

* 入力形式は問わない。テキストファイルで上記のような形式を実現しても良いし、言語内DSL
  として実現してもかまわない。
* プロパティファイルのキー名の.で区切られた部分を単位として、キー名を階層化して管理できる事
    * x.y.z というキー名を以下のように記述できる

              x {
                y {
                  z=
                }
              }

* java.util.Propertiesオブジェクトへ変換するための方法を提供する事
* Java形式のプロパティファイルへのシリアライズをサポートする事