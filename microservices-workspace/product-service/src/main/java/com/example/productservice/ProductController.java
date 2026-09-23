package com.example.productservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Lấy thông tin port hiện tại từ cấu hình
    @Value("${server.port}")
    private String port;

    @GetMapping("/{id}")
    public Map<String, Object> getProductInfo(@PathVariable String id) {
        return Map.of(
                "productId", id,
                "productName", "Laptop Dell XPS 15",
                "price", 1500.0,
                "servedByPort", port // Trả về thông tin Port để kiểm tra Load Balancing
        );
    }
}