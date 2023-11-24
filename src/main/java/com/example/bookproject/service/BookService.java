package com.example.bookproject.service;

import com.example.bookproject.entity.Member;
import com.example.bookproject.entity.ReadBook;
import com.example.bookproject.model.AddReadBookDto;
import com.example.bookproject.repository.MemberRepository;
import com.example.bookproject.repository.ReadBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
  private final MemberRepository memberRepository;
  private final ReadBookRepository readBookRepository;

  public void addReadBook(Long memberId, AddReadBookDto.Request request) {
    Member member = memberRepository.findByMemberId(memberId);

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
