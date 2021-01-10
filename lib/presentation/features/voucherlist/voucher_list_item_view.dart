import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/domain/models/voucher.dart';

class VoucherListItemView extends StatelessWidget {
  final Voucher voucher;

  VoucherListItemView(
    this.voucher,
  );

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.all(8),
      child: Column(
        children: [
          Image.network(this.voucher.imageUrl),
          Text(
            this.voucher.name,
            style: TextStyle(fontSize: 22),
          )
        ],
      ),
    );
  }
}
