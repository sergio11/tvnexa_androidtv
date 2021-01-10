import '../models/voucher.dart';

abstract class VoucherRepository {
  Future<List<Voucher>> getAll();
}
