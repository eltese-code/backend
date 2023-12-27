package com.example.bookproject.book.repository;

import com.example.bookproject.member.entity.Member;
import com.example.bookproject.book.entity.ReadBook;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadBookRepository extends JpaRepository<ReadBook, Long> {
  Slice<ReadBook> findAllByMemberInAndReadBookIdLessThanOrderByReadBookIdDesc(List<Member> followedList, Long lastReadBookId, Pageable pageable);
  List<ReadBook> findAllByMember_MemberIdOrderByCreatedDateDesc(Long memberId);
}
