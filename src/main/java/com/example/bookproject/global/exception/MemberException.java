package com.example.bookproject.global.exception;

public class MemberException extends RuntimeException {
  private final ErrorCode errorCode;
  private final String description;

  public MemberException(ErrorCode errorCode) {
    this.errorCode = errorCode;
    this.description = errorCode.getExplanation();
  }
}
