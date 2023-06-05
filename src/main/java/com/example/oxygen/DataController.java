package com.example.oxygen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/data")
    public List<ProductData> getData() {
        String sql = "SELECT * FROM productdata";
        List<ProductData> data = jdbcTemplate.query(sql, (rs, rowNum) -> {
            ProductData obj = new ProductData();
            obj.setId(rs.getInt("id"));
            obj.setName(rs.getString("name"));
            obj.setSize(rs.getString("size"));
            obj.setWeight(rs.getFloat("weight"));
            obj.setYear(rs.getInt("year"));
            obj.setDescription(rs.getString("description"));
            obj.setPrice(rs.getFloat("price"));
            obj.setPhotoUrl("photoUrl");

            return obj;
        });

        return data;
    }
}

