import 'package:flutter_clean_architecture/data/datasources/remote/fake_voucher_remote_data_source.dart';
import 'package:flutter_clean_architecture/data/repositories/voucher_repository_impl.dart';
import 'package:flutter_clean_architecture/domain/usecases/vouchers/get_vouchers_recommended_use_case.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/voucher_list_view_model.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/voucher_list_view_model_impl.dart';
import 'package:flutter_clean_architecture/presentation/injection/modules/base_module.dart';
import 'package:ioc/ioc.dart';

class VoucherModule extends BaseModule{

  void register(){
    Ioc().bind('VoucherFirebaseDataSource', (ioc) => FakeVoucherRemoteDataSource());
    Ioc().bind('VoucherRepository', (ioc) => VoucherRepositoryImpl(Ioc().use('VoucherFirebaseDataSource')));
    Ioc().bind('GetVouchersRecommendedUseCase', (ioc) => GetVouchersRecommendedUseCase(Ioc().use('VoucherFirebaseDataSource')));
    Ioc().bind('VoucherListViewModel', (ioc) => VoucherListViewModelImpl(Ioc().use('GetVouchersRecommendedUseCase')));
  }

  static VoucherListViewModel provideModule(){
    return BaseModule.provide('VoucherListViewModel');
  }
}