# FitTrack

Android workout logger built with Jetpack Compose. Browse exercise categories, log session duration, and review your workout history from one shared dashboard.

## Features

- Dashboard with workout count, total minutes, categories, and history
- Category → exercise list → exercise detail navigation
- Duration validation with live error feedback
- Success dialog after logging a workout
- Swipe left on a history item to remove it
- Clear button with confirmation to wipe the full history
- Session-only history (logs reset when the app process is recreated)

## Screens

| Screen | Route | What it does |
| --- | --- | --- |
| Dashboard | `dashboard` | Stats, categories, history |
| Exercise list | `exercises/{category}` | Exercises for one category |
| Exercise detail | `detail/{exerciseId}` | Log a workout duration |

## Architecture

- **UI:** Jetpack Compose + Material 3
- **Navigation:** Navigation Compose with route arguments
- **State:** One `WorkoutViewModel` exposing `WorkoutUiState` via `mutableStateOf`
- **Data flow:** Screens receive state and send events up (UDF)
- **Lists:** `LazyColumn` with stable `key = { it.id }`

## Requirements

- Android Studio
- JDK 17
- Android SDK with `compileSdk 37`
- Android 8.0 (API 26) or later device/emulator (`minSdk 26`)

## Run

1. Open the project in Android Studio
2. Sync Gradle
3. Run the `app` configuration on an emulator or device

```bash
./gradlew :app:assembleDebug
```
