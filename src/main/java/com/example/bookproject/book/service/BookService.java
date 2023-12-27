package com.example.bookproject.book.service;

import com.example.bookproject.book.entity.ReadBook;
import com.example.bookproject.book.model.AddReadBookDto;
import com.example.bookproject.book.repository.ReadBookRepository;
import com.example.bookproject.global.exception.ErrorCode;
import com.example.bookproject.global.exception.MemberException;
import com.example.bookproject.member.entity.Member;
import com.example.bookproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
  private final MemberRepository memberRepository;
  private final ReadBookRepository readBookRepository;

  public void addReadBook(Long memberId, AddReadBookDto.Request request) {
    Member member = memberRepository.findByMemberId(memberId)
        .orElseThrow(() -> new MemberException(ErrorCode.MEMBER_DOES_NOT_EXIST));

    readBookRepository.save(ReadBook.builder()
        .member(member)
        .bookTitle(request.getBookTitle())
        .bookAuthor(request.getBookAuthor())
        .bookPreference(request.getBookPreference())
        .bookComment(request.getBookComment())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .build());
  }
}
