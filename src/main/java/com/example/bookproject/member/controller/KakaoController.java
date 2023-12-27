package com.example.bookproject.member.controller;

import com.example.bookproject.book.model.ReadBookTitleDto;
import com.example.bookproject.member.model.LoginWithKakaoRequestDto;
import com.example.bookproject.member.model.SignupWithKakaoRequestDto;
import com.example.bookproject.member.model.StartWithKakaoResponseDto;
import com.example.bookproject.member.service.KakaoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {
  private final KakaoService kakaoService;

  @GetMapping("/start/kakao")
  public ResponseEntity<StartWithKakaoResponseDto> startWithKakao(@RequestParam(required = false) String code) {
    return ResponseEntity.ok(kakaoService.startWithKakao(code));
  }

  @PostMapping("/signup/kakao")
  public ResponseEntity<Void> signUpWithKakao(@RequestBody SignupWithKakaoRequestDto request) {
    kakaoService.signUpWithKakao(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/login/kakao")
  public ResponseEntity<List<ReadBookTitleDto>> loginWithKakao(@RequestBody LoginWithKakaoRequestDto request) {
    return ResponseEntity.ok(kakaoService.loginWithKakao(request.getKakaoAccessToken()));
  }
}
