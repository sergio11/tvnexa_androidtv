
import 'package:flutter_clean_architecture/data/datasources/remote/services/JokeService.dart';
import 'package:flutter_clean_architecture/data/mappers/JokeDataMapper.dart';
import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';
import 'package:flutter_clean_architecture/domain/repositories/AbstractJokeRepository.dart';

class JokeRemoteDataSource extends AbstractJokeRepository {

  final JokeService jokeService;

  JokeRemoteDataSource(this.jokeService);

  @override
  Future<List<JokeModel>> getRandomJokeList(int count, String category) async {

    var response = await jokeService.getRandomJoke();

    if (response.isSuccessful) {
      // successful request

      var jokeList = List<JokeModel>();
      jokeList.add(JokeDataMapper.mapper(response.body));

      return Future<List<JokeModel>>.value(jokeList);

    } else {
      // error from server
      final code = response.statusCode;
      final error = response.error;
      throw Exception("$code -> $error");
    }

  }

}