package com.example.bookproject.timeline.model;

import com.example.bookproject.book.constants.BookPreference;
import com.example.bookproject.book.entity.ReadBook;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimelinePostDto {
  private String memberProfilePicUrl;
  private String memberNickname;
  private Long readBookId;
  private String bookTitle;
  private String bookAuthor;
  private String bookComment;
  private BookPreference prefer;
  private LocalDate endDate;

  public static TimelinePostDto fromEntity(ReadBook readBook) {
    return TimelinePostDto.builder()
        .memberProfilePicUrl(readBook.getMember().getProfilePicUrl())
        .memberNickname(readBook.getMember().getNickname())
        .readBookId(readBook.getReadBookId())
        .bookTitle(readBook.getBookTitle())
        .bookAuthor(readBook.getBookAuthor())
        .bookComment(readBook.getBookComment())
        .prefer(readBook.getBookPreference())
        .endDate(readBook.getEndDate())
        .build();
  }
}
