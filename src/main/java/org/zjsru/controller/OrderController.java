package org.zjsru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zjsru.dao.OrderRepository;
import org.zjsru.domain.Order;
import org.zjsru.domain.OrderRequest;
import org.zjsru.domain.TicketRequest;
import org.zjsru.service.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/ticket")
    public Order createTicketOrder(@RequestBody TicketRequest request) {
        return orderService.createTicketOrder(
                request.getSpotId(),
                request.getUserId(),
                request.getUserName(),
                request.getUserPhone(),
                request.getPointsToUse()
        );
    }

    @GetMapping("/user")
    public List<Order> getOrdersByUserId(@RequestParam Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        try {
            boolean success = orderService.cancelOrder(orderId);
            if (success) {
                return ResponseEntity.ok(Map.of("success", true, "message", "订单已取消"));
            } else {
                return ResponseEntity.status(400).body(Map.of("success", false, "message", "无法取消订单"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    // 合并两个方法为一个，使用可选参数
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(
            @RequestParam(required = false) String username,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (username != null && !username.isEmpty()) {
            // 如果有用户名参数，执行搜索
            List<Order> orders = orderService.getOrdersByUsername(username);
            return ResponseEntity.ok(orders);
        } else {
            // 否则执行分页查询
            Page<Order> orders = orderService.getAllOrders(page - 1, size);
            return ResponseEntity.ok(orders);
        }
    }

    // 删除订单
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            orderRepository.deleteById(orderId);
            return ResponseEntity.ok(Map.of("success", true, "message", "订单删除成功"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", "删除订单失败"));
        }
    }
}
