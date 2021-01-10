abstract class BaseUseCase<TResult> {
  Future<TResult> perform();
}
