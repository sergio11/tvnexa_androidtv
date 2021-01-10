
abstract class AbstractUseCase<TResult> {
  Future<TResult> perform();
}
