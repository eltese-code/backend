package com.example.bookproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  KAKAO_LOGIN_FAILED("카카오 로그인이 실패하였습니다."),
  KAKAO_LOGIN_CANCELED("카카오 로그인이 취소되었습니다."),
  MEMBER_DOES_NOT_EXIST("존재하지 않는 회원입니다.");

  private final String explanation;
}
