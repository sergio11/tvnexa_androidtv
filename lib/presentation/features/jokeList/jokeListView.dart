import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';
import 'package:flutter_clean_architecture/presentation/features/jokeList/jokeListItemView.dart';

class JokeListView extends StatelessWidget{

  final List<JokeModel> jokeList;

  JokeListView(this.jokeList);

  @override
  Widget build(BuildContext context) {
    return Flexible(
      child: ListView.builder(
          padding: EdgeInsets.all(8),
          itemCount: jokeList.length,
          itemBuilder: (BuildContext context, int index) => JokeListItemView(jokeList[index])),
    );
  }
}