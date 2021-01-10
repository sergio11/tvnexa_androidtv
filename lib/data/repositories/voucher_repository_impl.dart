import 'package:flutter_clean_architecture/domain/models/voucher.dart';
import 'package:flutter_clean_architecture/domain/repositories/voucher_repository.dart';

class VoucherRepositoryImpl implements VoucherRepository {

  VoucherRepository voucherRemoteDataSource;

  VoucherRepositoryImpl(this.voucherRemoteDataSource);

  @override
  Future<List<Voucher>> getAll() {
    return this.voucherRemoteDataSource.getAll();
  }
}
