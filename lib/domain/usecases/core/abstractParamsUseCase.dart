
abstract class AbstractParamsUseCase<TResult, TParam> {
  Future<TResult> perform(TParam param);
}
