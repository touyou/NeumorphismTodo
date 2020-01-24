# ToDo
インターン課題：簡易ToDoアプリ 藤井陽介

## 使用した技術要素

- JPA
- Lombok
- AWS EC2 / RDS / multi-AZ / ALB
  - 構成は[こちら](https://qiita.com/KevinFQ/items/119521ebd12bb7890761)を参考にしました。
- Neumorphism design

## 全体の設計・構成についての説明

- View(resources) ... templatesに入ったThymeleafでの表示に関する処理
- controller ... viewへのデータの受け渡しやルーティング
- service ... repositoryへのアクセスやビジネスロジック
- repository ... データベースとの接続
- entity/form ... 各種Beans
- validation/constraints ... 入力値のバリデーションに関する実装
- exception ... カスタムエラー

## 開発環境のセットアップ手順

- MySQL, Java, Gradleをセットアップ
- 必要に応じて`create database tododb;`をMySQLで実行する
- resources内に`todo.properties`を作成し`adminPass=[admin password]`の行を追加して保存する。
- docker-composeで起動することも可能。ただしタイミングがおかしい時があるのでその際はアプリ側のDockerだけ起動しなおせば繋がる。
- `./gradlew build && ./gradlew docker`でアプリケーションのDockerイメージを生成することができる。テスト実行中MySQLへの接続があるのでローカルでtododbがあるMySQLを立てておく必要はある。
- Tableの作成はcreateTable.ddlを使う、もしくは最初の起動で作成される
- 起動はgradleのbootRun

### AWSでのセットアップ（Dockerを使わないもの）

0. AWS側のJavaを13にアップデート
0. GradleでbootJarを生成
0. 以下のようなシェルスクリプトと./build/lib/Todo-[version].jarを送る
```shell script
#!/bin/sh
dbUrl="jdbc:mysql:replication://{AWS DB Hosts}/tododb"
dbUser="{AWS DB User}"
dbPass="{AWS DB Password}"
java -jar -Dspring.profiles.active=aws -Dspring.datasource.url=$dbUrl -Dspring.datasource.username=$dbUser -Dspring.datasource.password=$dbPass $1
```

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
