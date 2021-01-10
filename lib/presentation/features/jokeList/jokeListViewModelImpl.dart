import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';
import 'package:flutter_clean_architecture/domain/usecases/jokes/getRandomJokeListUseCase.dart';
import 'package:flutter_clean_architecture/presentation/features/jokeList/jokeListViewModel.dart';
import 'package:rxdart/rxdart.dart';

class JokeListViewModelImpl extends AbstractJokeListViewModel {

  var jokeListSubject = PublishSubject<List<JokeModel>>();

  final GetRandomJokeListUseCase getRandomJokeListUseCase;

  JokeListViewModelImpl(this.getRandomJokeListUseCase);

  @override
  Stream<List<JokeModel>> get jokeList => jokeListSubject.stream;

  void refresh() async {
    try {
      jokeListSubject = PublishSubject<List<JokeModel>>();
      var jokeList = await getRandomJokeListUseCase.perform(Params(10, null));
      if (jokeList != null && jokeList.isNotEmpty) {
        jokeListSubject.sink.add(jokeList);
      }
    } catch (e) {
      jokeListSubject.sink.addError(e);
    }
  }

  @override
  void dispose() {
    jokeListSubject.close();
  }

}
