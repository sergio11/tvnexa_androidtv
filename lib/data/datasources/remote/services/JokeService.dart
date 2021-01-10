import 'package:chopper/chopper.dart';
import 'package:flutter_clean_architecture/data/entities/remote/jokeDTO.dart';

part "JokeService.chopper.dart";

@ChopperApi(baseUrl: "/jokes")
abstract class JokeService extends ChopperService {

  @Get(path: '/random')
  Future<Response<JokeDTO>> getRandomJoke();

  static JokeService create([ChopperClient client]) =>
      _$JokeService(client);
}