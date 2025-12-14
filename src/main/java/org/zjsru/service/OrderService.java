package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zjsru.dao.OrderRepository;
import org.zjsru.dao.ScenicSpotRepository;
import org.zjsru.dao.UserRepository;
import org.zjsru.domain.Order;
import org.zjsru.domain.ScenicSpot;
import org.zjsru.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ScenicSpotRepository scenicSpotRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public Order createTicketOrder(Long spotId, Long userId, String userName,
                                   String userPhone, int pointsToUse) {
        // 获取景点信息
        ScenicSpot spot = scenicSpotRepository.findById(spotId)
                .orElseThrow(() -> new RuntimeException("景点不存在"));

        // 获取用户信息
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        // 计算价格
        double originalPrice = spot.getPrice(); // 假设景点有price属性
        double maxDiscount = originalPrice * 0.2; // 最多20%折扣
        int maxPointsToUse = (int) (maxDiscount * 100); // 假设1积分=1分钱

        // 确保用户不会使用超过拥有的积分和最大可用积分
        pointsToUse = Math.min(pointsToUse, user.getPoints());
        pointsToUse = Math.min(pointsToUse, maxPointsToUse);

        double actualPrice = originalPrice - (pointsToUse / 100.0);
        int pointsEarned = (int) (actualPrice * 10); // 消费1元获得10积分

        // 创建订单
        Order order = new Order();
        order.setSpotId(spotId);
        order.setUserId(userId);
        order.setUserName(userName);
        order.setUserPhone(userPhone);
        order.setSpotName(spot.getName());
        order.setSpotImageUrl(spot.getImageUrl());
        order.setOrderTime(LocalDateTime.now());
        order.setStatus("PAID");
        order.setOriginalPrice(originalPrice);
        order.setActualPrice(actualPrice);
        order.setPointsUsed(pointsToUse);
        order.setPointsEarned(pointsEarned);

        // 扣除用户积分并增加新积分
        userService.deductPoints(userId, pointsToUse);
        userService.addPoints(userId, pointsEarned);

        return orderRepository.save(order);
    }
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    public boolean cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("订单不存在"));

        if (!"PAID".equals(order.getStatus())) {
            throw new RuntimeException("只有已支付的订单可以取消");
        }

        // 退还积分
        if (order.getPointsUsed() > 0) {
            userService.addPoints(order.getUserId(), order.getPointsUsed());
        }

        // 扣除获得的积分
        if (order.getPointsEarned() > 0) {
            userService.deductPoints(order.getUserId(), order.getPointsEarned());
        }

        order.setStatus("CANCELLED");
        orderRepository.save(order);
        return true;
    }
    public Page<Order> getAllOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderTime").descending()));
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUserNameContainingIgnoreCase(username);
    }
}
