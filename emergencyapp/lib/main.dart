import 'dart:ui';
import 'dart:async';
import 'dart:convert';
import 'package:emergencyapp/posts.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:emergencyapp/earthquake.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'emergency.dart';
import 'app_bar.dart';
import 'fire.dart';
import 'flood.dart';
import 'help.dart';

const Color darkBlue = Color.fromARGB(255, 18, 32, 47);

void main() {
  runApp(MyApp());
}

String dashboard = "ACİL ";

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData.dark().copyWith(
        scaffoldBackgroundColor: darkBlue,
      ),
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: CustomAppBar(dashboard),
        body: Center(
          child: MyWidget(),
        ),
      ),
    );
  }
}

class MyWidget extends StatelessWidget {

  @override


  Widget build(BuildContext context) {
    return GridView.count(
      primary: false,
      padding: const EdgeInsets.all(20),
      crossAxisSpacing: 10,
      mainAxisSpacing: 10,
      crossAxisCount: 2,
      children: <Widget>[
        TextButton.icon(

          icon: const Icon(Icons.call),

          label: const Text('Deprem'),
          style: TextButton.styleFrom(backgroundColor: Colors.blueGrey[700]),
          onPressed: () {
            Navigator.push(
              context,
              CupertinoPageRoute(
                builder: (ctxt) => Earthquake(),
              ),
            );
          },
        ),
        TextButton.icon(
          icon: const Icon(Icons.call),
          label: const Text('Yangın'),
          style: TextButton.styleFrom(backgroundColor: Colors.blueGrey[700]),
          onPressed: () {
            Navigator.push(
              context,
              CupertinoPageRoute(
                builder: (ctxt) => Fire(),
              ),
            );
          },
        ),
        TextButton.icon(
          icon: const Icon(Icons.call),
          label: const Text('Sel'),
          style: TextButton.styleFrom(backgroundColor: Colors.blueGrey[700]),
          onPressed: () {
            Navigator.push(
              context,
              CupertinoPageRoute(
                builder: (ctxt) => Flood(),
              ),
            );
          },
        ),
        TextButton.icon(
          icon: const Icon(Icons.call),
          label: const Text('İmdat'),
          style: TextButton.styleFrom(backgroundColor: Colors.blueGrey[700]),
          onPressed: () {
            Navigator.push(
              context,
              CupertinoPageRoute(
                builder: (ctxt) => Help(),
              ),
            );
          },
        )
      ],
    );
  }
}



