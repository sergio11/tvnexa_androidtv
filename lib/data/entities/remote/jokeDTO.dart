
import 'package:json_annotation/json_annotation.dart';

part 'jokeDTO.g.dart';

@JsonSerializable()
class JokeDTO {

  @JsonKey(name: 'icon_url')
  final String iconUrl;
  @JsonKey(name: 'id')
  final String id;
  @JsonKey(name: 'url')
  final String url;
  @JsonKey(name: 'value')
  final String value;

  JokeDTO({
    this.iconUrl,
    this.id,
    this.url,
    this.value,
  });

  factory JokeDTO.fromJson(Map<String, dynamic> json) => _$JokeDTOFromJson(json);

  Map<String, dynamic> toJson() => _$JokeDTOToJson(this);
}
