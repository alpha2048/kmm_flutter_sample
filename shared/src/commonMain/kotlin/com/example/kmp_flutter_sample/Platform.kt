package com.example.kmp_flutter_sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform