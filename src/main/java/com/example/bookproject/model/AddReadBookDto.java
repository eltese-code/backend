package com.example.bookproject.model;

import com.example.bookproject.constants.BookPreference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;


public class AddReadBookDto {

  @Getter
  @Builder
  @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Request {
    private String bookTitle;
    private String bookAuthor;
    private BookPreference bookPreference;
    private String bookComment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
  }
}
