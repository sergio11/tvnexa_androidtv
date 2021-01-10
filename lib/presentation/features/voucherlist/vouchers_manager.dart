import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/domain/models/voucher.dart';
import 'package:flutter_clean_architecture/presentation/commons/no_result_view.dart';
import 'package:flutter_clean_architecture/presentation/commons/progress_indicator_view.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/voucher_list_view.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/voucher_list_view_model.dart';
import 'package:flutter_clean_architecture/presentation/injection/modules/voucher_module.dart';
import 'package:flutter_clean_architecture/presentation/utils/alert_helper.dart';

class VouchersManager extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return VouchersManagerState();
  }
}

class VouchersManagerState extends State<VouchersManager>
    with WidgetsBindingObserver {
  final VoucherListViewModel viewModel = VoucherModule.provideModule();

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
    return StreamBuilder<List<Voucher>>(
      stream: viewModel.vouchers,
      builder: (context, snapshot) {
        if (dataHasError(snapshot)) {
          AlertHelper.showSnackBar(context, snapshot.error.toString());
        }

        var vouchers = snapshot.data;
        if (null != vouchers) return VoucherListView(vouchers);

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

  bool dataHasError(AsyncSnapshot<List<Voucher>> snapshot) => snapshot.hasError;

  bool connectionIsActive(AsyncSnapshot<List<Voucher>> snapshot) =>
      snapshot.connectionState == ConnectionState.active;

  bool connectionIsWaiting(AsyncSnapshot<List<Voucher>> snapshot) =>
      snapshot.connectionState == ConnectionState.waiting &&
      snapshot.data == null;

  void refresh() {
    viewModel.refresh();
    setState(() {});
  }
}
