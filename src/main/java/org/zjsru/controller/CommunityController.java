package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjsru.dao.PostDetailRepository;
import org.zjsru.domain.Comment;
import org.zjsru.domain.Post;
import org.zjsru.domain.PostDetail;
import org.zjsru.domain.PostRequest;
import org.zjsru.service.CommunityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;
    @Autowired
    private PostDetailRepository postDetailRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts(@RequestParam(required = false) String topic) {
        if (topic != null && !topic.isEmpty()) {
            return communityService.getPostsByTopic(topic);
        }
        return communityService.getAllPosts();
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostRequest request, @RequestHeader("userId") Long userId) {
        try {
            Post post = communityService.createPost(request, userId);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return communityService.getCommentsByPostId(postId);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> createComment(
            @PathVariable Long postId,
            @RequestBody Map<String, String> request,
            @RequestHeader("userId") Long userId) {
        try {
            Comment comment = communityService.createComment(postId, request.get("content"), userId);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    // 在CommunityController中添加
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        try {
            Post post = communityService.getPostById(postId);
            PostDetail detail = communityService.getPostDetail(postId);

            Map<String, Object> response = new HashMap<>();
            response.put("post", post);
            response.put("detail", detail);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    @PutMapping("/posts/{postId}/views")
    public ResponseEntity<?> incrementViews(@PathVariable Long postId) {
        try {
            PostDetail detail = communityService.getPostDetail(postId);
            detail.setViews(detail.getViews() + 1);
            postDetailRepository.save(detail);
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}