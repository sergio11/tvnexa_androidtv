import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';

class JokeListItemView extends StatelessWidget {

  final JokeModel jokeModel;

  JokeListItemView(
    this.jokeModel,
  );

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(8),
      child: Column(
        children: [
          Image.network(this.jokeModel.iconUrl),
          Text(
            this.jokeModel.value,
            style: TextStyle(fontSize: 22),
          )
        ],
      ),
    );
  }
}
