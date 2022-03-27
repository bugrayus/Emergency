import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget with PreferredSizeWidget {
  @override
  final Size preferredSize;
  final String title;



  CustomAppBar(
    this.title,
  ) : preferredSize = Size.fromHeight(50.0);

  @override
  Widget build(BuildContext context) {
    return AppBar(

        backgroundColor: Colors.blueGrey[700],
        title: Text(
          title,
          style: TextStyle(color: Colors.white),
        ));
  }
}
