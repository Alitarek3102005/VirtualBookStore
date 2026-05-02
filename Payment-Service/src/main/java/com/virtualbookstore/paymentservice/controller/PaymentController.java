package com.virtualbookstore.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.virtualbookstore.paymentservice.dto.PaymentDto;
import com.virtualbookstore.paymentservice.dto.PaymentRequest;
import com.virtualbookstore.paymentservice.dto.StripeResponse;
import com.virtualbookstore.paymentservice.service.PaymentService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/pay")
    public ResponseEntity<StripeResponse> pay(@RequestBody PaymentRequest paymentRequest){
        StripeResponse stripeResponse = paymentService.payWithStripe(paymentRequest);
        return ResponseEntity.status(200).body(stripeResponse);
    }

    @PostMapping("/stripe/webhook")
    public ResponseEntity<String> handleStripeWebhook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {

        String endpointSecret = "whsec_32b06a0c51562a2a9d5e220ad3dfcfdbe45b0e31b487533452442681a2d5daee";

        try {
            Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);

            if ("checkout.session.completed".equals(event.getType())) {
                Session session;
                if (event.getDataObjectDeserializer().getObject().isPresent()) {
                    session = (Session) event.getDataObjectDeserializer().getObject().get();
                } else {
                    session = (Session) event.getDataObjectDeserializer().deserializeUnsafe();
                }

                String paymentId = session.getMetadata().get("paymentId");

                if (paymentId != null) {
                    System.out.println("Payment Success! Updating DB for Payment ID: " + paymentId);
                    paymentService.confirmPayment(Long.parseLong(paymentId));
                }

            } else if ("checkout.session.expired".equals(event.getType())) {
                Session session;
                if (event.getDataObjectDeserializer().getObject().isPresent()) {
                    session = (Session) event.getDataObjectDeserializer().getObject().get();
                } else {
                    session = (Session) event.getDataObjectDeserializer().deserializeUnsafe();
                }

                String paymentId = session.getMetadata().get("paymentId");

                if (paymentId != null) {
                    System.out.println("Payment Expired/Cancelled for Payment ID: " + paymentId);
                    paymentService.cancelPayment(Long.parseLong(paymentId));
                }
            } else {
                System.out.println("Unhandled event type: " + event.getType());
            }

            return ResponseEntity.ok("");

        } catch (EntityNotFoundException enf) {
            System.err.println("Database Error: " + enf.getMessage());
            return ResponseEntity.status(400).body("No such Payment");
        } catch (Exception e) {
            System.err.println("Webhook Error: " + e.getMessage());
            return ResponseEntity.status(400).body("Webhook Error");
        }
    }
    @GetMapping("/{paymentId}")
    public ResponseEntity<Object> getPayment(@PathVariable String paymentId){
    	try {
    	PaymentDto payment =  paymentService.getPayment(Long.parseLong(paymentId));
    	return ResponseEntity.status(200).body(payment);
    	}catch(EntityNotFoundException enf)
    	{
    		return ResponseEntity.status(400).body(enf.getMessage());
    	}
    }


}
