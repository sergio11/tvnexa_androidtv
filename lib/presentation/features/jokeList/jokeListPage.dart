import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/presentation/features/jokeList/jokeListManager.dart';

class JokeListPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Jokes"),
        ),
        body: buildContent());
  }

  Widget buildContent() {
    return Container(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          JokeListManager(),
        ],
      ),
    );
  }
}