# ToDo
インターン課題：簡易ToDoアプリ 藤井陽介

## 使用した技術要素

- JPA
- Lombok

## 全体の設計・構成についての説明

- View ... templatesに入ったThymeleafでの表示に関する処理
- Controller ... viewへのデータの受け渡しやルーティング
- Service ... Repositoryへのアクセスやビジネスロジック
- Repository ... データベースとの接続
- Entity/Form ... 各種クラス
- Validation/Constraints ... 入力値のバリデーションに関する実装

## 開発環境のセットアップ手順

- MySQL, Java, Gradleをセットアップ
- Tableの作成はcreateTable.ddlを使う、もしくは最初の起動で作成される
- 起動はgradleのbootRun~~~~

### memo: テストデータの作成

0. `$ mysql.server start`でmysqlを起動
0. アプリを起動する
0. `$ mysql -uroot`でmysql入る
0. `> use tododb;`でデータベース`tododb`に。
0. `> show tables;`で`todo`という名前のテーブルができていることを確認。
0. `> insert into todo values (0, '2020-01-20', '2020-01-24', 0, 'intern');`の形式でデータ作成。

### memo: command

- `> select * from todo;`
- `> delete from todo;`
- `$ mysql.server stop`
