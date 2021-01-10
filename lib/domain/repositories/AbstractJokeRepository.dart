import '../models/jokeModel.dart';


abstract class AbstractJokeRepository {

  /// Retrieve a random chuck joke in JSON format.
  Future<List<JokeModel>> getRandomJokeList(int count, String category);


}