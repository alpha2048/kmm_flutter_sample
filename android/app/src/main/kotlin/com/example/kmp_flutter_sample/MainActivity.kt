package com.example.kmp_flutter_sample

import io.flutter.embedding.android.FlutterActivity
// MethodChannelのため
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodCall

class MainActivity: FlutterActivity() {

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        val channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "platform_method/kmp")
        channel.setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
            if (methodCall.method == "getGreeting") {
                val greet = Greeting().platformNameWithString(this)
                result.success(greet)
            }
            else {
                result.notImplemented()
            }
        }
    }
}
