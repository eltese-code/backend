package com.example.bookproject.member.repository;

import com.example.bookproject.member.constants.MemberType;
import com.example.bookproject.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByMemberId(Long memberId);
  boolean existsByMemberTypeAndId(MemberType memberType, Long id);
  Optional<Member> findByMemberTypeAndId(MemberType memberType, Long id);
}
