package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjsru.dao.CommentRepository;
import org.zjsru.dao.PostDetailRepository;
import org.zjsru.dao.PostRepository;
import org.zjsru.domain.Comment;
import org.zjsru.domain.Post;
import org.zjsru.domain.PostDetail;
import org.zjsru.domain.PostRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostDetailRepository postDetailRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> getPostsByTopic(String topic) {
        return postRepository.findByTopicOrderByCreatedAtDesc(topic);
    }
    public Comment createComment(Long postId, String content, Long userId) {
        String username = userService.getUserById(userId).orElseThrow().getUsername();

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(userId);
        comment.setUsername(username);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }
    public Post createPost(PostRequest request, Long userId) {
        String username = userService.getUserById(userId).orElseThrow().getUsername();

        Post post = new Post();
        post.setUserId(userId);
        post.setUsername(username);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setTopic(request.getTopic()); // 设置话题
        post.setCreatedAt(LocalDateTime.now());
        post.setImages(request.getImages());

        Post savedPost = postRepository.save(post);

        // 创建帖子详情
        PostDetail detail = new PostDetail();
        detail.setPost(savedPost);
        detail.setLocation(request.getLocation());
        detail.setTravelDate(request.getTravelDate());
        detail.setUpdatedAt(LocalDateTime.now());
        postDetailRepository.save(detail);

        return savedPost;
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }

    public PostDetail getPostDetail(Long postId) {
        return postDetailRepository.findByPostId(postId);
    }
}