package com.example.kmp_flutter_sample

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        val t = platform.name
        return "Hello, ${platform.name}!"
    }

    fun platformNameWithString(context: SPref): String {
        val t = context.getFlutterSavedData()
        return "Hello, ${platform.name}! with data: $t"
    }
}