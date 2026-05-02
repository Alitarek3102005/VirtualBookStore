package com.virtualbookstore.paymentservice.service;

import com.virtualbookstore.paymentservice.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.ProductData;
import com.stripe.param.checkout.SessionCreateParams.Mode;
import com.virtualbookstore.paymentservice.dto.PaymentDto;
import com.virtualbookstore.paymentservice.dto.PaymentRequest;
import com.virtualbookstore.paymentservice.dto.StripeResponse;
import com.virtualbookstore.paymentservice.model.Payment;
import com.virtualbookstore.paymentservice.repository.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaymentService {
    	
    @Autowired
	private PaymentRepository paymentRepository;
    @Autowired
    private OrderClient orderClient;

    @Value("${Stripe.secretKey}")
    private String stripeSecretKey;

    public StripeResponse payWithStripe(PaymentRequest paymentRequest) {
        Stripe.apiKey = stripeSecretKey;
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setStatus("PENDING");
        payment.setMethod("STRIPE");

        payment.setOrderId(paymentRequest.getOrderId());

        payment = paymentRepository.save(payment);

        ProductData productData = ProductData.builder()
                .setName(paymentRequest.getName())
                .build();

        PriceData priceData = PriceData.builder()
                .setCurrency(paymentRequest.getCurrency() != null ? paymentRequest.getCurrency() : "EGP")
                .setUnitAmount(paymentRequest.getAmount())
                .setProductData(productData)
                .build();

        LineItem lineItem = LineItem.builder()
                .setQuantity(paymentRequest.getQuantity())
                .setPriceData(priceData)
                .build();

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(Mode.PAYMENT)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setSuccessUrl("http://localhost:4200/success")
                .setCancelUrl("http://localhost:4200/cart")
                .putMetadata("paymentId", String.valueOf(payment.getPaymentId()))
                .addLineItem(lineItem)
                .build();

        Session session = null;
        try {
            session = Session.create(params);
        } catch (StripeException e) {
            return StripeResponse.builder()
                    .status("FAILED")
                    .message(e.getMessage())
                    .build();
        }

        return StripeResponse.builder()
                .status("SUCCESS")
                .message("Payment session created")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }

    public void confirmPayment(Long paymentId){
        Payment pendingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        pendingPayment.setStatus("SUCCESS");
        paymentRepository.save(pendingPayment);
        try {
            orderClient.updateOrderStatus(pendingPayment.getOrderId(), "PAID");
            System.out.println("Successfully notified Order Service: Order " + pendingPayment.getOrderId() + " is PAID.");
        } catch (Exception e) {
            System.err.println("CRITICAL: Payment succeeded, but failed to reach Order Service: " + e.getMessage());
        }
    }
    public void cancelPayment(Long paymentId){
    	Payment pendingPayment = paymentRepository.findById(paymentId)
    			.orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    	pendingPayment.setStatus("CANCELLED");
    	paymentRepository.save(pendingPayment);
    }
    public PaymentDto getPayment(Long paymentId) {
    	Payment payment = paymentRepository.findById(paymentId)
    			.orElseThrow(()-> new EntityNotFoundException("No Such Payment"));
    	return buildPaymentDto(payment);
    }
    
    public PaymentDto buildPaymentDto(Payment payment) {
    	return PaymentDto.builder()
    			.amount(payment.getAmount())
    			.method(payment.getMethod())
    			.paymentId(payment.getPaymentId())
    			.status(payment.getStatus())
    			.createdAt(payment.getDate())
    			.build();
    }

}