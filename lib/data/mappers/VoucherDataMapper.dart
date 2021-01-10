import 'package:flutter_clean_architecture/domain/models/voucher.dart';
import 'package:flutter_clean_architecture/data/entities/remote/VoucherRemoteEntity.dart';

abstract class VoucherDataMapper {
  static Voucher mapper(VoucherRemoteEntity voucherRemoteEntity) {
    return Voucher.newInstance(voucherRemoteEntity.id,
        voucherRemoteEntity.description, voucherRemoteEntity.url);
  }
}
