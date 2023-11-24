package com.example.bookproject.repository;

import com.example.bookproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByMemberId(Long memberId);
}
