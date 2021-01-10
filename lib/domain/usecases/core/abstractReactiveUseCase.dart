
abstract class AbstractReactiveUseCase<TResult> {
  Stream<TResult> perform();
}
