package org.zjsru.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zjsru.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    // 添加按用户名模糊查询的方法
    List<Order> findByUserNameContainingIgnoreCase(String userName);
}
