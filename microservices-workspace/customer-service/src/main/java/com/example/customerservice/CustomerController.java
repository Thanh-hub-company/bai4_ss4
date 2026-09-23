package com.example.customerservice;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping("/{id}")
    public Map<String, Object> getCustomerInfo(@PathVariable String id) {
        return Map.of("customerId", id, "name", "Nguyen Van A", "email", "nguyenvana@gmail.com");
    }
}