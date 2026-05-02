package com.example.apigateway; // ⚠️ Make sure this matches your actual package at the top!

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 1. Authentication Service
                .route("auth-service", r -> r
                        .path("/api/auth/**", "/api/user/**","/api/admin/**","/api/puplisher/**")
                        .uri("lb://authentication-service"))

                // 2. Catalog Service (Contains singular and plural to prevent 404s!)
                .route("catalog-service", r -> r
                        .path("/api/book/**", "/api/book", "/api/books/**","/api/category/**")
                        .uri("lb://catalog-service"))

                // 3. Cart Service
                .route("cart-service", r -> r
                        .path("/api/cart/**")
                        .uri("lb://cart-service"))

                // 4. Payment Service
                .route("payment-service", r -> r
                        .path("/api/payment/**")
                        .uri("lb://payment-service"))

                // 5. Order Service
                .route("order-service", r -> r
                        .path("/api/order/**", "/api/orders/**")
                        .uri("lb://order-service"))

                // 6. Reviews Service
                .route("reviews-service", r -> r
                        .path("/api/reviews/**")
                        .uri("lb://reviews-service"))

                .build();
    }


    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
