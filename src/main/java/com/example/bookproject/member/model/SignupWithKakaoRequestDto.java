package com.example.bookproject.member.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupWithKakaoRequestDto {
  private String kakaoAccessToken;
  private String nickname;
}
