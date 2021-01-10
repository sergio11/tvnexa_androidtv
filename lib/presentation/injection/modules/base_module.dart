import 'package:ioc/ioc.dart';

abstract class BaseModule{

  void register();

  static T provide<T>(String className)=> Ioc().use<T>(className);
}