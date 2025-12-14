package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zjsru.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
}