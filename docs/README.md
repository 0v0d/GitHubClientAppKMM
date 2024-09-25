## アプリ仕様

Kotlin Multiplatformを使用した、GitHubリポジトリ検索アプリです。</br>
AndroidアプリはJetpack Compose、iOSアプリはSwiftUIを使用しており、共通のビジネスロジックはKotlinで共有しています。

<img src="app_android.mp4" width="314" alt="Androidアプリのデモ動画">

<img src="app_ios.mp4" width="314" alt="iOSアプリのデモ動画">

## 開発環境

### 開発言語

- **Kotlin**
- **Swift**

### Android

- **IDE**: Android Studio Ladybug Feature Drop | 2024.2.2 Canary 3
- **コンパイルSDK**: 34
- **最小SDK**: 24
- **ターゲットSDK**: 34
- **Java**: VERSION_11
- **Gradle**: 8.9
- **Gradle Plugin**: 8.1.4
- **Kotlin**: 2.0.0

### iOS

- **IDE**: Xcode 16.0
- **Swift**: 6.0
- **開発ターゲット**: iOS 18.0
- **Minimum Deployment**: iOS 16.6

### 対象OS

- **iOS**: 18.0以降
- **Android**: 14以降

## 使用技術スタック

### 共通

- **Kotlinx Serialization**: シリアライゼーション/デシリアライゼーション
- **Ktor**: HTTPクライアント
- **Koin**: 依存性注入 (DI)
- **Kotlinx Coroutines**: 非同期処理
- **Cache4k**: キャッシュ

### Android

- **Jetpack Compose**: 宣言的UI
- **Coil**: 画像読み込み

### iOS

- **SwiftUI**: 宣言的UI
- **Ktor Client (Darwin)**: iOS用HTTPクライアント

## プロジェクト構成

- **shared**: 共通のビジネスロジック (Kotlinで実装)
- **ComposeApp**: Android UI (Jetpack Composeで実装)
- **iOSApp**: iOS UI (SwiftUIで実装)
