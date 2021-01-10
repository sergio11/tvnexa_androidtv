import 'package:flutter_clean_architecture/domain/models/voucher.dart';
import 'package:flutter_clean_architecture/domain/repositories/voucher_repository.dart';

import '../base_use_case.dart';

class GetVouchersRecommendedUseCase implements BaseUseCase<List<Voucher>> {
  VoucherRepository voucherRepository;

  GetVouchersRecommendedUseCase(this.voucherRepository);

  @override
  Future<List<Voucher>> perform() {
    return this.voucherRepository.getAll();
  }
}
