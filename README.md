# Bitcoin Signal Dashboard (Local-Only)

An Android app for tracking **Bitcoin market signals** in real time — built for privacy and speed.  
📱 No login. Local data only. Adjustable thresholds. Historical logs. In-app charts. CSV export.  
⚠️ **Not financial advice** — for informational and educational purposes only.

---

## 📊 Features

- **Adjustable thresholds** for ETF flows, exchange flows, funding rates, macro triggers.
- **Advanced scenario probability engine** — auto-updates based on signal conditions.
- **Historical log** stored locally.
- **Push notifications** when thresholds are crossed.
- **In-app charts** for quick visualization.
- **CSV export** for offline analysis.
- **Local-only** — no server, no cloud, no tracking.
- **Spacious dashboard UI** optimized for Android.

---

## 🛠 Build Details

- Language: Kotlin + Jetpack Compose
- Local database: Room
- Charts: MPAndroidChart
- CSV: OpenCSV
- Notifications: Android Notification Manager
- Min SDK: 26

---

## 📦 How to Get the APK

This repo uses **GitHub Actions** to build the app automatically.

### Steps:
1. **Go to the Actions tab** above.
2. Select the **Build APK** workflow.
3. Click **Run workflow** or wait for it to trigger on push.
4. After it completes, open the run and scroll to **Artifacts**.
5. Download the file named `app-debug.apk`.
6. Sideload on your Android device (enable "Install from unknown sources" if needed).

---

## ⚠️ Disclaimer

This application is for **educational and informational purposes only**.  
It is **not** financial advice. Cryptocurrency markets are volatile — trade at your own risk.
