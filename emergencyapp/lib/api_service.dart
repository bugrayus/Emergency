import 'dart:convert';

import 'package:device_info_plus/device_info_plus.dart';
import 'package:http/http.dart' as http;

class APIServices {
  static String url = 'http://localhost:8080';

  static Future login() async {
    return await http.post(
        Uri.parse(url+"/login"),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
          'mail': "deviceId",
      }),
    );
  }


}
