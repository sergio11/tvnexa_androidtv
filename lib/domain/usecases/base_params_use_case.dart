abstract class BaseParamsUseCase<TResult, TParam> {
  Future<TResult> perform(TParam param);
}
