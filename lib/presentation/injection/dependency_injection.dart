import 'package:flutter_clean_architecture/presentation/injection/modules/base_module.dart';
import 'package:flutter_clean_architecture/presentation/injection/modules/voucher_module.dart';

class DependencyInjection{

  List<BaseModule> modules = [VoucherModule()];

  void inject(){
    modules.forEach((module)=> module.register());
  }

}