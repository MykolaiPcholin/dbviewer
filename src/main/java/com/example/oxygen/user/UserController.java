package com.example.oxygen.user;

import com.example.oxygen.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-users")
    public List<User> getData() {
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));

            return user;
        });

        return users;
    }
}
