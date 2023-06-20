package com.example.oxygen.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/get-items")
    public List<Product> getData() {
        String sql = "SELECT * FROM items";
        List<Product> items = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product item = new Product();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setSize(rs.getString("size"));
            item.setWeight(rs.getFloat("weight"));
            item.setYear(rs.getInt("year"));
            item.setDescription(rs.getString("description"));
            item.setPrice(rs.getFloat("price"));
            item.setPhotoUrl("photoUrl");

            return item;
        });

        return items;
    }
}

