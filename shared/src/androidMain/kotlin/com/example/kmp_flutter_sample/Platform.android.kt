package com.example.kmp_flutter_sample

import android.app.Activity
import android.content.Context
import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

//private val Context.sharedPreferencesDataStore: DataStore<Preferences> by
//preferencesDataStore("FlutterSharedPreferences")

actual typealias SPref = Activity
actual fun SPref.getFlutterSavedData(): String {
    val preferences = this.getSharedPreferences(
        "FlutterSharedPreferences",
        Context.MODE_PRIVATE
    )
    return preferences.getString("flutter.savedDataFromFlutter", "データなし") ?: "データなし"

    /// SharedPreferencesAsync(DataStore)から取るなら以下だが、ファイル競合が起きるので動作不可
//    val value: String?
//    runBlocking {
//        val preferencesKey = stringPreferencesKey("savedDataFromFlutter")
//        val preferenceFlow: Flow<String?> =
//            this@getFlutterSavedData.application.sharedPreferencesDataStore.data.map { preferences -> preferences[preferencesKey] }
//        value = preferenceFlow.firstOrNull()
//    }
//    return value ?: "データなし"
}