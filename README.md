# ToDo
インターン課題：簡易ToDoアプリ 藤井陽介

## 使用した技術要素

// TODO

## 全体の設計・構成についての説明

// TODO

## 開発環境のセットアップ手順

// TODO

### memo: テストデータの作成

0. 起動する
0. `$ mysql -uroot`でmysql入る
0. `> use tododb;`でデータベース`tododb`に。
0. `> show tables;`で`todo`という名前のテーブルができていることを確認。
0. `> insert into todo values (0, '2020-01-20', '2020-01-24', 0, 'intern');`の形式でデータ作成。

### memo: command

- `> select * from todo;`
- `> delete from todo;`
