// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'jokeDTO.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

JokeDTO _$JokeDTOFromJson(Map<String, dynamic> json) {
  return JokeDTO(
    iconUrl: json['icon_url'] as String,
    id: json['id'] as String,
    url: json['url'] as String,
    value: json['value'] as String,
  );
}

Map<String, dynamic> _$JokeDTOToJson(JokeDTO instance) => <String, dynamic>{
      'icon_url': instance.iconUrl,
      'id': instance.id,
      'url': instance.url,
      'value': instance.value,
    };
