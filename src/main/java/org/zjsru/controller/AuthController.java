package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zjsru.domain.LoginRequest;
import org.zjsru.domain.RegisterRequest;
import org.zjsru.domain.User;
import org.zjsru.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        Optional<User> user = userService.authenticateUser(request.getUsername(), request.getPassword());
        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("userId", user.get().getId()); // 返回用户 ID
            response.put("username", user.get().getUsername()); // 返回用户名
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "用户名或密码错误"));
        }
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest request) {
        Optional<User> user = userService.authenticateAdmin(request.getUsername(), request.getPassword());
        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("username", user.get().getUsername()); // 确保返回用户名
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "管理员用户名或密码错误"));
        }
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            User user = userService.registerUser(request.getUsername(), request.getPassword(), request.getRole());
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("username", user.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("success", false, "message", "注册失败：用户名已存在"));
        }
    }
}
