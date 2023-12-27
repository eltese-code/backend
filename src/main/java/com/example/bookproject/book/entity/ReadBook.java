package com.example.bookproject.book.entity;

import com.example.bookproject.book.constants.BookPreference;
import com.example.bookproject.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long readBookId;
  @ManyToOne
  private Member member;
  private String bookTitle;
  private String bookAuthor;
  private BookPreference bookPreference;
  private String bookComment;
  private LocalDate startDate;
  private LocalDate endDate;
  @CreatedDate
  private LocalDateTime createdDate;
}
