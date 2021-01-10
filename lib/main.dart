import 'package:flutter/material.dart';
import 'package:flutter_clean_architecture/presentation/features/voucherlist/voucher_list_page.dart';
import 'package:flutter_clean_architecture/presentation/injection/dependency_injection.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {

    DependencyInjection().inject();

    return MaterialApp(
      title: 'Flutter Clean Architecture',
      theme: ThemeData(
        primarySwatch: Colors.amber,
      ),
      home: VoucherListPage(),
    );
  }
}
