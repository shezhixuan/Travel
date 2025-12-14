package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjsru.dao.UserRepository;
import org.zjsru.domain.User;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // 注意：实际应用中应对密码进行加密
        user.setRole(role);
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password)); // 注意：实际应用中应使用加密密码验证
    }

    public Optional<User> authenticateAdmin(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password) && "ADMIN".equals(user.getRole()));
    }
    // 获取用户积分
    public int getUserPoints(Long userId) {
        System.out.println("查询用户积分的用户 ID: " + userId); // 打印用户 ID
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            int points = userOptional.get().getPoints();
            System.out.println("用户积分: " + points); // 打印查询结果
            return points;
        } else {
            System.out.println("用户不存在"); // 打印错误信息
            return 0;
        }
    }
    // 增加用户积分
    public void addPoints(Long userId, int points) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        user.setPoints(user.getPoints() + points);
        userRepository.save(user);
    }

    // 扣除用户积分
    public boolean deductPoints(Long userId, int points) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPoints() >= points) {
                user.setPoints(user.getPoints() - points);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
    //添加获取用户信息的方法
    public Optional<User> getUserById(Long userId) {
        System.out.println("查询用户信息的用户 ID: " + userId); // 打印用户 ID
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            System.out.println("用户信息: " + userOptional.get()); // 打印查询结果
        } else {
            System.out.println("用户不存在"); // 打印错误信息
        }
        return userOptional;
    }
    // 添加带原因的积分
    public boolean addPointsWithReason(Long userId, int points, String reason) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPoints(user.getPoints() + points);
            userRepository.save(user);

            // 这里可以添加积分记录到数据库
            // pointsRecordService.recordPointsChange(userId, points, reason);

            return true;
        }
        return false;
    }
}
