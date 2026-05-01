package com.virtualbookstore.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtualbookstore.paymentservice.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
