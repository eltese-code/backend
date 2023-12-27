package com.example.bookproject.member.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StartWithKakaoResponseDto {
  private boolean isMember;
  private String accessToken;
  private String refreshToken;
}
