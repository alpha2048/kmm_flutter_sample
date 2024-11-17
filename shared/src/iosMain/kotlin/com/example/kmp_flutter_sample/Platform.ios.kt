package com.example.kmp_flutter_sample

import platform.UIKit.UIDevice
import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual typealias SPref = NSObject
actual fun SPref.getFlutterSavedData(): String {
    return NSUserDefaults.standardUserDefaults.stringForKey("flutter.savedDataFromFlutter") ?: ""
}
