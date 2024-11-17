package com.example.kmp_flutter_sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class SPref
expect fun SPref.getFlutterSavedData(): String