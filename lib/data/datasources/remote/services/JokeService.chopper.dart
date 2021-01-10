// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'JokeService.dart';

// **************************************************************************
// ChopperGenerator
// **************************************************************************

// ignore_for_file: always_put_control_body_on_new_line, always_specify_types, prefer_const_declarations
class _$JokeService extends JokeService {
  _$JokeService([ChopperClient client]) {
    if (client == null) return;
    this.client = client;
  }

  @override
  final definitionType = JokeService;

  @override
  Future<Response<JokeDTO>> getRandomJoke() {
    final $url = '/jokes/random';
    final $request = Request('GET', $url, client.baseUrl);
    return client.send<JokeDTO, JokeDTO>($request);
  }
}
