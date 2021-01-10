import 'package:chopper/chopper.dart';
import 'package:flutter_clean_architecture/data/datasources/remote/JokeRemoteDataSource.dart';
import 'package:flutter_clean_architecture/data/datasources/remote/services/JokeService.dart';
import 'package:flutter_clean_architecture/data/repositories/JokeRepositoryImpl.dart';
import 'package:flutter_clean_architecture/domain/usecases/jokes/getRandomJokeListUseCase.dart';
import 'package:flutter_clean_architecture/presentation/features/jokeList/jokeListViewModel.dart';
import 'package:flutter_clean_architecture/presentation/features/jokeList/jokeListViewModelImpl.dart';
import 'package:flutter_clean_architecture/presentation/injection/modules/abstractModule.dart';
import 'package:ioc/ioc.dart';

class JokeListModule extends AbstractModule {


  static const String JOKE_NETWORK_SERVICE_KEY = "jokeNetworkService";
  static const String JOKE_LIST_VIEW_MODEL_KEY = "jokeListViewModel";
  static const String JOKE_REMOTE_DATA_SOURCE_KEY = "jokeRemoteDataSource";
  static const String JOKE_REPOSITORY_KEY = "jokeRepository";
  static const String GET_RANDOM_JOKES_USE_CASE = "getRandomJokesUseCase";


  void register(){
    Ioc().bind(JOKE_NETWORK_SERVICE_KEY, (ioc) => provideJokeService());
    Ioc().bind(JOKE_REMOTE_DATA_SOURCE_KEY, (ioc) => JokeRemoteDataSource(Ioc().use(JOKE_NETWORK_SERVICE_KEY)));
    Ioc().bind(JOKE_REPOSITORY_KEY, (ioc) => JokeRepositoryImpl(Ioc().use(JOKE_REMOTE_DATA_SOURCE_KEY)));
    Ioc().bind(GET_RANDOM_JOKES_USE_CASE, (ioc) => GetRandomJokeListUseCase(Ioc().use(JOKE_REPOSITORY_KEY)));
    Ioc().bind(JOKE_LIST_VIEW_MODEL_KEY, (ioc) => JokeListViewModelImpl(Ioc().use(GET_RANDOM_JOKES_USE_CASE)));
  }


  JokeService provideJokeService() {

    final chopper = ChopperClient(
      baseUrl: "https://api.chucknorris.io",
      interceptors: [HttpLoggingInterceptor()],
      services: [
        // inject the generated service
        JokeService.create()
      ],
    );

    return JokeService.create(chopper);
  }


  static AbstractJokeListViewModel provideViewModel(){
    return AbstractModule.provide(JOKE_LIST_VIEW_MODEL_KEY);
  }
}