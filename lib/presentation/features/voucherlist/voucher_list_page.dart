import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/vouchers_manager.dart';

class VoucherListPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Vouchers"),
        ),
        body: buildContent());
  }

  Widget buildContent() {
    return Container(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          VouchersManager(),
        ],
      ),
    );
  }
}