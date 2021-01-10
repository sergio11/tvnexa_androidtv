import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/presentation/injection/dependencyInjection.dart';
import 'presentation/features/jokeList/jokeListPage.dart';
import 'package:logging/logging.dart';

void main() {
  _setupLogging();
  runApp(MyApp());
}

void _setupLogging() {
  Logger.root.level = Level.ALL;
  Logger.root.onRecord.listen((rec) {
    print('${rec.level.name}: ${rec.time}: ${rec.message}');
  });
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {

    DependencyInjection().inject();

    return MaterialApp(
      title: 'Flutter Clean Architecture',
      theme: ThemeData(
        primarySwatch: Colors.amber,
      ),
      home: JokeListPage(),
    );
  }

}
