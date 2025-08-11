# Bitcoin Signal App (Unsigned Debug APK)

This repository contains a local-only Android app (Kotlin + Jetpack Compose) implementing a simple Bitcoin signal dashboard and editable spreadsheet of thresholds.

## How to use

1. Push this repository to your GitHub account.
2. In GitHub go to Actions → Build APK and the workflow will run and produce an artifact `app-debug.apk`.
3. Download the APK and sideload on your Android device (enable install from unknown sources).

Notes:
- This debug APK is unsigned and for testing only.
- Some features use public APIs (CoinGecko) and require network access.
- You can edit thresholds and CSVs inside the app.

