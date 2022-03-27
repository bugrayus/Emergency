import 'package:flutter/material.dart';
import 'app_bar.dart';
import 'package:url_launcher/url_launcher.dart';

class Emergency extends StatefulWidget {
  @override
  _EmergencyState createState() => _EmergencyState();
}

class _EmergencyState extends State<Emergency>
    with SingleTickerProviderStateMixin {
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

  String dashboard = "ACİL DURUMLAR";
  String text_content = "Burası detaylı bir acil durum bilgilendirmesidir.";

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
                    print('clicked');
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
                    const url = 'https://google.com';
                    launchURL(url);
                  },
                  child: Text('GÜVENLİ ALANLAR'),
                )
              ]),
          Row(
              mainAxisAlignment:
                  MainAxisAlignment.center, //Center Row contents horizontally
              crossAxisAlignment:
                  CrossAxisAlignment.center, //Center Row contents vertically
              children: <Widget>[Text(text_content)])
        ]));
  }
}
