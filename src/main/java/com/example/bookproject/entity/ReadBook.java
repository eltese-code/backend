package com.example.bookproject.entity;

import com.example.bookproject.constants.BookPreference;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
