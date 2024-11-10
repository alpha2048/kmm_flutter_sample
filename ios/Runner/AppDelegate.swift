import Flutter
import UIKit
import Shared

@main
@objc class AppDelegate: FlutterAppDelegate {
    override func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        
        let controller : FlutterViewController = window?.rootViewController as! FlutterViewController
        let kmpChannel = FlutterMethodChannel(name:  "platform_method/kmp", binaryMessenger: controller as! FlutterBinaryMessenger)
        kmpChannel.setMethodCallHandler({
            (methodCall: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
            if (methodCall.method == "getGreeting") {
                let greet = Greeting().greet()
                result(greet)
            } else {
                result(FlutterMethodNotImplemented)
            }
        })
        
        GeneratedPluginRegistrant.register(with: self)
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }
}
