import 'package:flutter/material.dart';

abstract class AlertHelper{
  static void showSnackBar(BuildContext context, String errorMessage) async {
    await Future.delayed(Duration.zero);
    Scaffold.of(context)
        .showSnackBar(SnackBar(content: Text(errorMessage)));
  }
}