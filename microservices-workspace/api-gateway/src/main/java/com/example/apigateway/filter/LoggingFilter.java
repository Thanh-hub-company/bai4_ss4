package com.example.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. Lấy thông tin đường dẫn Path của Request
        String path = exchange.getRequest().getURI().getPath();

        // 2. In ra Console theo đúng yêu cầu bài tập
        logger.info("Incoming request to: {}", path);

        // 3. Cho phép Request tiếp tục đi sang Service đích
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // Thứ tự ưu tiên thực thi (0 là mức ưu tiên cao)
        return 0;
    }
}