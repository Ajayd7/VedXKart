package com.ved.vedxkart.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface ShoppingService {

    public ResponseEntity addCustomer(String customerName, String address, String country);

    public ResponseEntity addProduct(String productTitle, String productDescription);

    public ResponseEntity placeOrder(Long productId, Long customerId);
    
    public ResponseEntity updateStatus(Long orderId, String  status);

    public ResponseEntity fetchAllOrders();

}
