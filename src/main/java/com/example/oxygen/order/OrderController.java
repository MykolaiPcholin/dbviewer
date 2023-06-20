package com.example.oxygen.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-orders")
    public List<Order> getOrders() {
        String sql = "SELECT * FROM orders";
        List<Order> orders = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTotalCost(rs.getFloat("total_cost"));
            order.setUserComment(rs.getString("user_comment"));
            order.setCreatedAt(rs.getString("created_at"));

            List<OrderItem> orderItems = getOrderItems(order.getId());
            order.setOrderItems(orderItems);

            return order;

        });

    return orders;

    }

    private List<OrderItem> getOrderItems(int orderId) {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        List<OrderItem> orderItems = jdbcTemplate.query(sql, new Object[]{orderId}, (rs, rowNum) -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(rs.getInt("id"));
            orderItem.setOrderId(rs.getInt("order_id"));
            orderItem.setItemId(rs.getInt("item_id"));
            orderItem.setQuantity(rs.getInt("quantity"));

            return orderItem;
        });

        return orderItems;
    }

}