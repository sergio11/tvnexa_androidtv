import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/domain/models/voucher.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/voucher_list_item_view.dart';

class VoucherListView extends StatelessWidget{

  final List<Voucher> vouchers;

  VoucherListView(this.vouchers);

  @override
  Widget build(BuildContext context) {
    return Flexible(
      child: ListView.builder(
          padding: EdgeInsets.all(8),
          itemCount: vouchers.length,
          itemBuilder: (BuildContext context, int index) {
            return VoucherListItemView(vouchers[index]);
          }),
    );
  }
}