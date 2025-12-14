// PostDetailRepository.java
package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zjsru.domain.PostDetail;

public interface PostDetailRepository extends JpaRepository<PostDetail, Long> {
    PostDetail findByPostId(Long postId);
}