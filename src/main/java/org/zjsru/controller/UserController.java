package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zjsru.dao.ProductPurchaseRepository;
import org.zjsru.dao.UserRepository;
import org.zjsru.domain.ProductPurchase;
import org.zjsru.domain.User;
import org.zjsru.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductPurchaseRepository purchaseRepository;
    // UserController.java 新增方法
    @GetMapping("/{userId}/purchases")
    public ResponseEntity<?> getUserPurchases(@PathVariable Long userId) {
        try {
            List<ProductPurchase> purchases = purchaseRepository.findByUserId(userId);
            List<Map<String, Object>> response = purchases.stream()
                    .map(p -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", p.getId());
                        map.put("productId", p.getProduct().getId());
                        map.put("productName", p.getProduct().getName());
                        map.put("pricePaid", p.getPricePaid());
                        map.put("purchaseDate", p.getPurchaseDate());
                        map.put("imageUrl", p.getProduct().getImageUrl());
                        return map;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    @GetMapping("/{userId}/points")
    public ResponseEntity<?> getUserPoints(@PathVariable Long userId) {
        System.out.println("请求的用户 ID: " + userId); // 打印用户 ID
        int points = userService.getUserPoints(userId);
        System.out.println("查询到的用户积分: " + points); // 打印查询结果
        return ResponseEntity.ok(Map.of("points", points));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userId) {
        System.out.println("请求的用户 ID: " + userId); // 打印用户 ID
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("查询到的用户信息: " + user); // 打印查询结果
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());

            response.put("points", user.getPoints());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(Map.of("success", false, "message", "用户不存在"));
        }
    }
    // 获取所有用户
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 更新用户角色
    @PutMapping("/{userId}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("用户不存在"));

            String newRole = request.get("role");
            if (!"USER".equals(newRole) && !"ADMIN".equals(newRole)) {
                throw new RuntimeException("无效的角色类型");
            }

            user.setRole(newRole);
            userRepository.save(user);

            return ResponseEntity.ok(Map.of("success", true, "message", "用户角色更新成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
    @PostMapping("/{userId}/add-points")
    public ResponseEntity<?> addUserPoints(
            @PathVariable Long userId,
            @RequestBody Map<String, Object> request) {

        try {
            int points = (int) request.get("points");
            String reason = (String) request.get("reason");

            boolean success = userService.addPointsWithReason(userId, points, reason);

            if (success) {
                return ResponseEntity.ok(Map.of(
                        "success", true,
                        "message", "积分添加成功",
                        "newPoints", userService.getUserPoints(userId)
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "用户不存在"
                ));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }
}
