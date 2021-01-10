import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';
import 'package:flutter_clean_architecture/domain/repositories/AbstractJokeRepository.dart';
import 'package:flutter_clean_architecture/domain/usecases/core/abstractParamsUseCase.dart';


class GetRandomJokeListUseCase implements AbstractParamsUseCase<List<JokeModel>, Params> {

  AbstractJokeRepository jokeRepository;

  GetRandomJokeListUseCase(this.jokeRepository);

  @override
  Future<List<JokeModel>> perform(Params param) =>
      jokeRepository.getRandomJokeList(param.count, param.category);

}

class Params {

  final int count;
  final String category;

  Params(this.count, this.category);

}
