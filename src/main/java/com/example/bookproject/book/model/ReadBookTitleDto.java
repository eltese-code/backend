package com.example.bookproject.book.model;

import com.example.bookproject.book.entity.ReadBook;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadBookTitleDto {
  private String readBookTitle;

  public static ReadBookTitleDto fromEntity(ReadBook readBook) {
    return ReadBookTitleDto.builder()
        .readBookTitle(readBook.getBookTitle())
        .build();
  }
}
