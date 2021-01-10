abstract class BaseReactiveUseCase<TResult> {
  Stream<TResult> perform();
}
