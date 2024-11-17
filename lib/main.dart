import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:shared_preferences/shared_preferences.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter KMP Sample',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter KMP Demo Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const methodChannel = MethodChannel('platform_method/kmp');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              ElevatedButton(
                onPressed: () async {
                  final data = DateTime.now().toIso8601String();

                  final prefs = await SharedPreferences.getInstance();
                  await prefs.setString('savedDataFromFlutter',
                      "保存日: $data");

                  final SharedPreferencesAsync asyncPrefs = SharedPreferencesAsync();
                  await asyncPrefs.setString('savedDataFromFlutter',
                      "保存日: $data");

                  Fluttertoast.showToast(
                      msg: "保存したデータ: $data",
                      toastLength: Toast.LENGTH_LONG,
                      gravity: ToastGravity.BOTTOM,
                      timeInSecForIosWeb: 3,
                      backgroundColor: Colors.blueGrey,
                      textColor: Colors.white,
                      fontSize: 18.0);
                },
                child: Text(
                  "データを入れる",
                  style: Theme.of(context).textTheme.bodyLarge,
                ),
              ),
              const SizedBox(
                height: 16,
              ),
              ElevatedButton(
                onPressed: () async {
                  try {
                    final res = await methodChannel.invokeMethod('getGreeting');
                    print("Method Channelデータ取得: $res");
                    Fluttertoast.showToast(
                        msg: "データ取得: $res",
                        toastLength: Toast.LENGTH_LONG,
                        gravity: ToastGravity.BOTTOM,
                        timeInSecForIosWeb: 3,
                        backgroundColor: Colors.blueGrey,
                        textColor: Colors.white,
                        fontSize: 18.0);
                  } on PlatformException catch (e) {
                    print("Method Channelデータ取得エラー: $e");
                    Fluttertoast.showToast(
                        msg: "データ取得エラー: $e",
                        toastLength: Toast.LENGTH_LONG,
                        gravity: ToastGravity.BOTTOM,
                        timeInSecForIosWeb: 3,
                        backgroundColor: Colors.blueGrey,
                        textColor: Colors.white,
                        fontSize: 18.0);
                  }
                },
                child: Text(
                  "Method Channelからデータ取得",
                  style: Theme.of(context).textTheme.bodyLarge,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
