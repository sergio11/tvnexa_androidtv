import 'package:flutter/material.dart';

class NoResultView extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
      return Expanded(
        child: Center(
          child: Text("No Data Available"),
        ),
      );
    }
  }