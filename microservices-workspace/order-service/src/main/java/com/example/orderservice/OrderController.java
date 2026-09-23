package com.example.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/lb-product/{productId}")
    public ResponseEntity<?> getProductWithLoadBalancer(@PathVariable String productId) {
        // Dùng TÊN SERVICE đăng ký trên Eureka (PRODUCT-SERVICE)
        String productUrl = "http://PRODUCT-SERVICE/products/" + productId;

        try {
            // Tự động phân phối request luân phiên giữa các Port 8082 và 8084
            Map productData = restTemplate.getForObject(productUrl, Map.class);

            Map<String, Object> response = new HashMap<>();
            response.put("orderId", "ORD-100");
            response.put("productInfo", productData);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Trả về HTTP 503 khi toàn bộ instance Product Service bị tắt
            ApiResponseError error = new ApiResponseError(
                    HttpStatus.SERVICE_UNAVAILABLE.value(),
                    "Service PRODUCT-SERVICE is currently unavailable"
            );
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
        }
    }
}