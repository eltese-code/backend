package com.example.bookproject.controller;

import com.example.bookproject.model.TimelinePostDto;
import com.example.bookproject.service.TimelineService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TimelineController {
  private final TimelineService timelineService;

  @GetMapping(value = "/timeline/{memberId}")
  public ResponseEntity<List<TimelinePostDto>> getTimeline(@PathVariable Long memberId,
      @RequestParam(required = false) Long lastReadBookId) {
    return ResponseEntity.ok(timelineService.getTimelinePosts(memberId, lastReadBookId));
  }
}
