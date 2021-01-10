import 'package:flutter_clean_architecture/domain/models/voucher.dart';
import 'package:flutter_clean_architecture/presentation/features/base_view_model.dart';

abstract class VoucherListViewModel extends BaseViewModel{
  Stream<List<Voucher>> get vouchers;
  void refresh();
}