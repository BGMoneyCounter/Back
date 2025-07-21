# BGMoneyCounter Backend

ボードゲーム用お金管理システムのバックエンドAPI

## 概要

Spring Bootを使用したRESTful APIサーバーです。ボードゲームのお金管理機能を提供し、ゲーム作成、プレイヤー管理、お金の増減などの機能をサポートします。

## 技術スタック

- **Java**: 21
- **Spring Boot**: 3.x
- **Spring Data JPA**: データベースアクセス
- **H2 Database**: 開発用インメモリデータベース
- **Gradle**: ビルドツール
- **Lombok**: ボイラープレートコード削減

## 機能一覧

### Main画面機能
1. **ゲーム一覧取得** - `GET /gameList/view`
2. **ゲーム作成** - `POST /createGame`
3. **ゲーム参加** - `POST /joinGame`
4. **プレイヤー一覧表示** - `GET /game/view/{gameId}/playerList`
5. **プレイヤー選択** - `GET /game/view/{gameId}/{playerId}`

### GM画面機能
6. **GM画面表示** - `GET /gm/view/{gameId}`
7. **プレイヤー追加** - `POST /gm/joinPlayer`
8. **プレイヤー名変更** - `PUT /gm/changePlayerName`
9. **プレイヤー削除** - `DELETE /gm/deletePlayer`
10. **ゲーム名変更** - `PUT /gm/changeGameName`
11. **お金変更** - `PUT /gm/changeMoney`
12. **ゲーム終了** - `PUT /gm/finishedGame`

### Joiner画面機能
13. **Joiner画面表示** - `GET /joiner/view/{gameId}`
14. **プレイヤー名変更** - `PUT /joiner/changePlayerName`

## セットアップ

### 前提条件
- Java 21以上
- Gradle 7.x以上

### インストール手順

1. リポジトリをクローン
```bash
git clone https://github.com/BGMoneyCounter/Back.git
cd Back
```

2. 実行権限を付与
```bash
chmod +x gradlew
```

3. アプリケーションを起動
```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
./gradlew bootRun
```

4. サーバーが起動したら以下のURLでアクセス可能
- API: http://localhost:8080
- H2コンソール: http://localhost:8080/h2-console

## API仕様

### 認証
現在は認証機能なし（開発用）

### エンドポイント詳細

#### ゲーム管理
```
POST /createGame
Content-Type: application/json

{
  "gameName": "テストゲーム",
  "pass": "password123"
}
```

#### プレイヤー管理
```
POST /gm/joinPlayer
Content-Type: application/json

{
  "gameId": 1
}
```

#### お金変更
```
PUT /gm/changeMoney
Content-Type: application/json

{
  "playerId": 1,
  "money": 100
}
```

## データベース設計

### Games テーブル
- `game_id`: ゲームID（主キー）
- `game_name`: ゲーム名（ユニーク）
- `game_pass`: パスワード
- `end_flag`: 終了フラグ

### Players テーブル
- `player_id`: プレイヤーID（主キー）
- `game_id`: ゲームID（外部キー）
- `player_name`: プレイヤー名
- `gm_flag`: GMフラグ
- `money`: 所持金

## 開発

### ビルド
```bash
./gradlew build
```

### テスト実行
```bash
./gradlew test
```

### 本番用ビルド
```bash
./gradlew bootJar
```

## CORS設定

フロントエンドとの連携のため、以下のオリジンを許可：
- http://localhost:8081
- http://localhost:8082
- http://localhost:5173

## ライセンス

MIT License

## 貢献

1. このリポジトリをフォーク
2. フィーチャーブランチを作成 (`git checkout -b feature/amazing-feature`)
3. 変更をコミット (`git commit -m 'Add amazing feature'`)
4. ブランチにプッシュ (`git push origin feature/amazing-feature`)
5. プルリクエストを作成

## サポート

問題や質問がある場合は、GitHubのIssuesページで報告してください。
