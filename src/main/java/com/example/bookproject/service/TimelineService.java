package com.example.bookproject.service;

import com.example.bookproject.entity.Member;
import com.example.bookproject.model.TimelinePostDto;
import com.example.bookproject.repository.FollowRepository;
import com.example.bookproject.repository.MemberRepository;
import com.example.bookproject.repository.ReadBookRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineService {
  private final MemberRepository memberRepository;
  private final FollowRepository followRepository;
  private final ReadBookRepository readBookRepository;

  public List<TimelinePostDto> getTimelinePosts(Long memberId, Long lastReadBookId) {

    if (lastReadBookId == null) {
      lastReadBookId = Long.MAX_VALUE;
    }

    // 팔로우 계정 가지고 오기
    List<Member> followed = followRepository.findAllByFollowerIdAndIsAccepted(memberId, true)
        .stream().map(follow -> memberRepository.findByMemberId(follow.getFollowedId())).collect(Collectors.toList());

    // 본인 계정 목록에 추가
    followed.add(memberRepository.findByMemberId(memberId));

    // 포스트 가져오기
    return readBookRepository.findAllByMemberInAndReadBookIdLessThanOrderByReadBookIdDesc(followed, lastReadBookId, PageRequest.of(0, 20))
        .stream().map(TimelinePostDto::fromEntity).collect(Collectors.toList());
  }
}
