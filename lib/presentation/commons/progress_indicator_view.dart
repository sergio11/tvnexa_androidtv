import 'package:flutter/material.dart';

class ProgressIndicatorView extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Expanded(
        child: Center(
        child: CircularProgressIndicator(),
      ),
    );
  }
}