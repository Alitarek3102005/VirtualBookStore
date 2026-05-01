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
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/pay")
    public ResponseEntity<StripeResponse> pay(@RequestBody PaymentRequest paymentRequest){
        StripeResponse stripeResponse = paymentService.payWithStripe(paymentRequest);
        return ResponseEntity.status(200).body(stripeResponse);
    }
    
    @PostMapping("/stripe/webhook")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        //when deployed on AWS
    	String endpointSecret="";

        try {
            Event event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();
            
            String paymentId = session.getMetadata().get("paymentId");
            switch (event.getType()) {
            case "payment_intent.succeeded":
                paymentService.confirmPayment(Long.parseLong(paymentId));
                break;
            case "payment_intent.payment_failed":
                paymentService.cancelPayment(Long.parseLong(paymentId));
            	break;
            default:
               throw new Exception();
        }
            return ResponseEntity.ok("");
        }catch (EntityNotFoundException enf) {
            return ResponseEntity.status(400).body("No such Payment");
        } catch (Exception e) {
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
