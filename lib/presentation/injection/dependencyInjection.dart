import 'package:flutter_clean_architecture/presentation/injection/modules/abstractModule.dart';
import 'package:flutter_clean_architecture/presentation/injection/modules/jokeListModule.dart';

class DependencyInjection{

  List<AbstractModule> modules = [JokeListModule()];

  void inject(){
    modules.forEach((module)=> module.register());
  }

}