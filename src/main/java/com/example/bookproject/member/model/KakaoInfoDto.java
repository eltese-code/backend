package com.example.bookproject.member.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoInfoDto {
  @JsonProperty("id")
  private Long id;
}
