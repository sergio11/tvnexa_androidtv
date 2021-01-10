import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/domain/models/jokeModel.dart';
import 'package:flutter_clean_architecture/presentation/commons/no_result_view.dart';
import 'package:flutter_clean_architecture/presentation/commons/progress_indicator_view.dart';
import 'package:flutter_clean_architecture/presentation/features/jokeList/jokeListView.dart';
import 'package:flutter_clean_architecture/presentation/features/jokeList/jokeListViewModel.dart';
import 'package:flutter_clean_architecture/presentation/injection/modules/jokeListModule.dart';
import 'package:flutter_clean_architecture/presentation/utils/alertHelper.dart';

class JokeListManager extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return JokeListManagerState();
  }
}

class JokeListManagerState extends State<JokeListManager>
    with WidgetsBindingObserver {

  final AbstractJokeListViewModel viewModel = JokeListModule.provideViewModel();

  @override
  void initState() {
    super.initState();
    subscribe();
    refresh();
  }

  @override
  void dispose() {
    unsubscribe();
    viewModel.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<List<JokeModel>>(
      stream: viewModel.jokeList,
      builder: (context, snapshot) {
        if (dataHasError(snapshot)) {
          AlertHelper.showSnackBar(context, snapshot.error.toString());
        }

        var jokeList = snapshot.data;
        if (null != jokeList) return JokeListView(jokeList);

        if (connectionIsWaiting(snapshot)) {
          return ProgressIndicatorView();
        }

        return NoResultView();
      },
    );
  }

  void subscribe() {
    WidgetsBinding.instance.addObserver(this);
  }

  void unsubscribe() {
    WidgetsBinding.instance.removeObserver(this);
  }

  bool dataHasError(AsyncSnapshot<List<JokeModel>> snapshot) => snapshot.hasError;

  bool connectionIsActive(AsyncSnapshot<List<JokeModel>> snapshot) =>
      snapshot.connectionState == ConnectionState.active;

  bool connectionIsWaiting(AsyncSnapshot<List<JokeModel>> snapshot) =>
      snapshot.connectionState == ConnectionState.waiting &&
      snapshot.data == null;

  void refresh() {
    viewModel.refresh();
    setState(() {});
  }
}
