import 'package:emergencyapp/emergency.dart';
import 'package:flutter/material.dart';
import 'app_bar.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:emergencyapp/http_Service.dart';
import 'package:http/http.dart' as http;
import 'dart:convert' as convert;

class Fire extends StatefulWidget {
  @override
  _FireState createState() => _FireState();
}

class _FireState extends State<Fire> with SingleTickerProviderStateMixin {
  @override
  void initState() {
    super.initState();
  }

  launchURL(String url) async {
    if (await canLaunch(url)) {
      await launch(url);
    } else {
      throw "Could not launch $url";
    }
  }

  String dashboard = "YANGIN";
  String text_content =
      "Telaşa kapılmadan çevrede yangın ihbar düğmesi varsa ona basılmalıdır.";
  String text_contetn2 =
      "Yangın kapalı alandaysa yayılmasını önlemek için kapı ve pencereler kapatılmalıdır. ";
  String text_content3 =
      "Dumandan boğulmamak için yardım gelene kadar eğilerek ve sürünerek hareket edilmeli, ağız ve burun ıslak bez ya da mendille kapatılarak nefes alınmalıdır.";
  String text_content4 =
      "Kıyafetiniz alev almışsa; koşmadan durup yere yatarak yuvarlanılmalıdır. Battaniye türü örtüler alınarak alevler boğulmaya çalışılmalıdır.";

  @override
  Widget build(BuildContext context) {
    CustomAppBar(dashboard);
    return Scaffold(
        appBar: CustomAppBar(dashboard),
        body: Column(children: <Widget>[
          Container(
            height: 20,
          ),
          Row(
              mainAxisAlignment:
                  MainAxisAlignment.center, //Center Row contents horizontally
              crossAxisAlignment:
                  CrossAxisAlignment.center, //Center Row contents vertically
              children: <Widget>[
                MaterialButton(
                  minWidth: 200,
                  height: 200,
                  shape: CircleBorder(
                      side: BorderSide(
                          width: 1,
                          color: Colors.red.shade900,
                          style: BorderStyle.solid)),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: const <Widget>[
                      Icon(
                        Icons.priority_high_outlined,
                        color: Colors.white,
                        size: 145,
                      ),
                    ],
                  ),
                  color: Colors.red.shade900,
                  onPressed: () {
                    launch('tel:+90 5455919604');
                  },
                ),
              ]),
          Container(
            height: 40,
          ),
          Row(
              mainAxisAlignment:
                  MainAxisAlignment.center, //Center Row contents horizontally
              crossAxisAlignment:
                  CrossAxisAlignment.center, //Center Row contents vertically
              children: <Widget>[
                TextButton(
                  style: ButtonStyle(
                    foregroundColor:
                        MaterialStateProperty.all<Color>(Colors.blue),
                  ),
                  onPressed: () {
                    launch(
                        'https://www.google.com/maps/place//@38.6908332,39.1713543,16.29z/data=!4m17!1m11!4m10!1m4!2m2!1d39.2166259!2d38.673758!4e1!1m3!2m2!1d39.1693384!2d38.69422!3e0!3m4!1s0x4076c1a4cef088d5:0xd13681e8d6ac5d41!8m2!3d38.6922679!4d39.1710448');
                  },
                  child: Text('GÜVENLİ ALANLAR'),
                )
              ]),
          Column(
              mainAxisAlignment:
              MainAxisAlignment.center, //Center Row contents horizontally
              crossAxisAlignment: CrossAxisAlignment.center,
              //Center Row contents vertically
              children: <Widget>[
                Text(text_content +
                    text_contetn2 +
                    text_content3 +
                    text_content4)
              ])
        ]));
  }
}
