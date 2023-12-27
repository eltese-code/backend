package com.example.bookproject.member.repository;

import com.example.bookproject.member.entity.Follow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
  List<Follow> findAllByFollowerIdAndIsAccepted(Long followerId, Boolean isAccepted);
}
