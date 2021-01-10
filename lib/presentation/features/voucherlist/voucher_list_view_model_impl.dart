import 'package:flutter_clean_architecture/domain/models/voucher.dart';
import 'package:flutter_clean_architecture/domain/usecases/vouchers/get_vouchers_recommended_use_case.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/voucher_list_view_model.dart';
import 'package:rxdart/rxdart.dart';

class VoucherListViewModelImpl implements VoucherListViewModel{
  var voucherListSubject = PublishSubject<List<Voucher>>();

  GetVouchersRecommendedUseCase getVouchersRecommendedUseCase;

  Stream<List<Voucher>> get vouchers => voucherListSubject.stream;

  VoucherListViewModelImpl(this.getVouchersRecommendedUseCase);

  void refresh() async {
    try {
      voucherListSubject = PublishSubject<List<Voucher>>();
      var vouchers = await getVouchersRecommendedUseCase.perform();
      if (vouchers != null && vouchers.isNotEmpty) {
        voucherListSubject.sink.add(vouchers);
      }
    } catch (e) {
      voucherListSubject.sink.addError(e);
    }
  }

  @override
  void dispose() {
    voucherListSubject.close();
  }
}
