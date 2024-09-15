package com.cg.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Payment;
import com.cg.exception.PaymentNotFoundException;
import com.cg.service.PaymentServiceImpl;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @PostMapping("/createpayment/{orderId}/{accNo}")
    @PreAuthorize("hasAnyAuthority('dealer')")
    public ResponseEntity<Payment> createPaymentFromOrder(@PathVariable int orderId, @PathVariable long accNo) throws PaymentNotFoundException {
        Payment payment = paymentService.createPaymentFromOrder(orderId, accNo);
        return ResponseEntity.ok(payment);
    }
    
    @GetMapping("/byDealerName")
    @PreAuthorize("hasAnyAuthority('dealer')")
    public ResponseEntity<Payment> getPaymentByDealerName(Principal p) throws PaymentNotFoundException {
    	String name = p.getName();
    	Payment paymentByDealerName = paymentService.findPaymentByDealerName(name);
    	return ResponseEntity.ok(paymentByDealerName);
    }
    
    @GetMapping("/greaterThanTotalPrice/{totalPrice}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Payment>> findByTotalPriceGreaterThan(@PathVariable double totalPrice) throws PaymentNotFoundException {
    	List<Payment> byTotalPriceGreaterThan = paymentService.findByTotalPriceGreaterThan(totalPrice);
    	return ResponseEntity.ok(byTotalPriceGreaterThan);
    }
    
    @GetMapping("/lessThanTotalPrice/{totalPrice}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Payment>> findByTotalPriceLessThan(@PathVariable double totalPrice) throws PaymentNotFoundException {
    	List<Payment> byTotalPriceLessThan = paymentService.findByTotalPriceLessThan(totalPrice);
    	return ResponseEntity.ok(byTotalPriceLessThan);
    }
    
    @GetMapping("/orderDateBefore/{orderDate}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Payment>> findByOrderDateBefore(@PathVariable LocalDate orderDate) throws PaymentNotFoundException {
    	List<Payment> byOrderDateBefore = paymentService.findByOrderDateBefore(orderDate);
    	return ResponseEntity.ok(byOrderDateBefore);
    }
    
    @GetMapping("/orderDateAfter/{orderDate}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Payment>> findByOrderDateAfter(@PathVariable LocalDate orderDate) throws PaymentNotFoundException {
    	List<Payment> byOrderDateAfter = paymentService.findByOrderDateAfter(orderDate);
    	return ResponseEntity.ok(byOrderDateAfter);
    }
    
    @GetMapping("/last2Transactions")
    @PreAuthorize("hasAnyAuthority('admin')")
    public ResponseEntity<List<Payment>> getLast5Transactions() throws PaymentNotFoundException {
        List<Payment> payments = paymentService.getLast2Transactions();
        return ResponseEntity.ok(payments);
    }
}
