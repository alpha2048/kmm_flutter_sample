package com.example.kmp_flutter_sample

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun platformNameWithString(context: SPref): String {
        val savedData = context.getFlutterSavedData()
        return "Hello, ${platform.name}! with data: $savedData"
    }
}