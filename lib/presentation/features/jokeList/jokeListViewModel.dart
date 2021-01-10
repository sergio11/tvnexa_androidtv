import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';
import 'package:flutter_clean_architecture/presentation/features/AbstractViewModel.dart';

abstract class AbstractJokeListViewModel extends AbstractViewModel{

  Stream<List<JokeModel>> get jokeList;

  void refresh();
}