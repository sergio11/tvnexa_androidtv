import 'package:flutter_clean_architecture/data/entities/remote/jokeDTO.dart';
import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';

abstract class JokeDataMapper {

  static JokeModel mapper(JokeDTO jokeRemoteEntity) =>
      JokeModel(
          iconUrl: jokeRemoteEntity.iconUrl,
          id: jokeRemoteEntity.id,
          value: jokeRemoteEntity.value,
          url: jokeRemoteEntity.url
      );
}
