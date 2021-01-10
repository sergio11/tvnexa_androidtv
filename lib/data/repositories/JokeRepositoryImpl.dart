import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';
import 'package:flutter_clean_architecture/domain/repositories/AbstractJokeRepository.dart';

class JokeRepositoryImpl extends AbstractJokeRepository {

  final AbstractJokeRepository jokeRemoteDataSource;

  JokeRepositoryImpl(this.jokeRemoteDataSource);

  @override
  Future<List<JokeModel>> getRandomJokeList(int count, String category) =>
      jokeRemoteDataSource.getRandomJokeList(count, category);
}