package com.example.bookproject.member.service;

import com.example.bookproject.book.entity.ReadBook;
import com.example.bookproject.book.repository.ReadBookRepository;
import com.example.bookproject.global.exception.ErrorCode;
import com.example.bookproject.global.exception.MemberException;
import com.example.bookproject.member.constants.MemberType;
import com.example.bookproject.member.entity.Member;
import com.example.bookproject.member.model.KakaoInfoDto;
import com.example.bookproject.member.model.KakaoLoginDto;
import com.example.bookproject.member.model.SignupWithKakaoRequestDto;
import com.example.bookproject.member.model.StartWithKakaoResponseDto;
import com.example.bookproject.member.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class KakaoService {
  private final MemberRepository memberRepository;
  private final ReadBookRepository readBookRepository;
  private final RestClient restClient;

  @Value("${kakao.rest-api-key}")
  private String kakaoRestApiKey;
  @Value("${kakao.redirect-uri}")
  private String kakaoRedirectUri;
  @Value("${kakao.login-uri}")
  private String kakaoLoginUri;
  @Value("${kakao.info-uri}")
  private String kakaoInfoUri;

  public StartWithKakaoResponseDto startWithKakao(String code) {
    KakaoLoginDto kakaoLoginDto = getKakaoLoginDto(restClient, code);
    KakaoInfoDto kakaoInfoDto = getKakaoInfoDto(restClient, kakaoLoginDto.getAccessToken());

    return StartWithKakaoResponseDto.builder()
        .isMember(memberRepository.existsByMemberTypeAndId(MemberType.KAKAO, kakaoInfoDto.getId()))
        .accessToken(kakaoLoginDto.getAccessToken())
        .refreshToken(kakaoLoginDto.getRefreshToken())
        .build();
  }

  public Long signUpWithKakao(SignupWithKakaoRequestDto request) {
    Member member = memberRepository.save(Member.builder()
        .memberType(MemberType.KAKAO)
        .id(getKakaoInfoDto(restClient, request.getKakaoAccessToken()).getId())
        .nickname(request.getNickname())
        .build());

    return member.getMemberId();
  }

  public List<ReadBook> loginWithKakao(String kakaoAccessToken) {
    Member member = memberRepository.findByMemberTypeAndId(MemberType.KAKAO, getKakaoInfoDto(restClient, kakaoAccessToken).getId())
        .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_DOES_NOT_EXIST));

    return readBookRepository.findAllByMember_MemberIdOrderByCreatedDateDesc(member.getMemberId());
  }

  private KakaoLoginDto getKakaoLoginDto(RestClient restClient, String authCode) {
    KakaoLoginDto kakaoLoginDto = restClient.post()
        .uri(kakaoLoginUri + "?grant_type=authorization_code&client_id={clientId}&redirect_uri={redirectUri}&code={code}", kakaoRestApiKey, kakaoRedirectUri, authCode)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(KakaoLoginDto.class);

    checkIfValidKakaoLoginDto(kakaoLoginDto);

    return kakaoLoginDto;
  }

  private void checkIfValidKakaoLoginDto(KakaoLoginDto kakaoLoginDto) {
    if (kakaoLoginDto == null) {
      throw new MemberException(ErrorCode.KAKAO_LOGIN_FAILED);
    }
  }

  private KakaoInfoDto getKakaoInfoDto(RestClient restClient, String accessToken) {
    KakaoInfoDto kakaoInfoDto = restClient.post()
        .uri(kakaoInfoUri)
        .header("Authorization", "Bearer " + accessToken)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .body(KakaoInfoDto.class);

    checkIfValidKakaoInfoDto(kakaoInfoDto);

    return kakaoInfoDto;
  }

  private void checkIfValidKakaoInfoDto(KakaoInfoDto kakaoInfoDto) {
    if (kakaoInfoDto == null) {
      throw new MemberException(ErrorCode.KAKAO_LOGIN_FAILED);
    }
  }
}
