import 'dart:convert';
import 'package:http/http.dart' as http;
import 'post_model.dart';

class HttpService {
  static String postsURL = "https://localhost:/8080";

  static Future postMail() async {
    return await http.post(Uri.parse(postsURL+"/emergency"));

  }

}