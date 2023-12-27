package com.example.bookproject.book.controller;

import com.example.bookproject.book.model.AddReadBookDto;
import com.example.bookproject.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @PostMapping(value = "/readbook/{memberId}")
  public ResponseEntity<Void> addReadBook(@PathVariable Long memberId, @RequestBody AddReadBookDto.Request request) {
    bookService.addReadBook(memberId, request);
    return ResponseEntity.ok().build();
  }
}
