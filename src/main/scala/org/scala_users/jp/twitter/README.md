# テーマ：Twitter連携

以下のように外部コマンドの出力をパイプで受け取り、そのテキストをGoogle翻
訳を使って英語に翻訳した結果をツイートする。

    (例)groovyの場合
    $ echo "こんにちは、Groovy!" | groovy translateAndTweet.groovy

ツイート内容例:

    Hello, Groovy! #javaonetokyo_bof

* 標準入力で渡す文字列は"こんにちは、<言語名>!"を基本とするが、他の文字
  列でも構わない。
* 翻訳した文字列の他に、(多分事前に決定されるはずの)ハッシュタグを末尾に
  付ける。
* WebAPIのアクセスキー等は各自自分で用意する。
