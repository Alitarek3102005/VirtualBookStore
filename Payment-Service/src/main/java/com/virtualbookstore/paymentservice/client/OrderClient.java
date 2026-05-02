package com.virtualbookstore.paymentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service")
public interface OrderClient {
    @PutMapping("/api/order/{id}/updateStatus")
    void updateOrderStatus(
            @PathVariable("id") Long id,
            @RequestParam("orderStatus") String orderStatus
    );
}