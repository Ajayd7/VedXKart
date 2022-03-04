package com.ved.vedxkart.controller;

import com.ved.vedxkart.dto.PlaceOrderDTO;
import com.ved.vedxkart.dto.UpdateStatusDTO;
import com.ved.vedxkart.model.Customer;
import com.ved.vedxkart.model.Product;
import com.ved.vedxkart.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    //Create Customer API endpoint
    @PostMapping(value = "/addCustomer")
    private ResponseEntity addCustomer(@RequestBody Customer customer) {
        return shoppingService.addCustomer(
                customer.getCustomerName(), customer.getAddress(), customer.getCountry());
    }

    //Create Product API endpoint
    @PostMapping(value = "/addProduct")
    private ResponseEntity addProduct(@RequestBody Product product) {
        return shoppingService.addProduct(
                product.getProductTitle(), product.getProductDescription());
    }

    //Place order API endpoint
    @PostMapping(value = "/placeOrder")
    private ResponseEntity placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        return shoppingService.placeOrder(
                placeOrderDTO.getProductId(), placeOrderDTO.getCustomerId());
    }

    //Update Status of the order API endpoint
    @PutMapping(value = "/updateStatus")
    private ResponseEntity updateStatus(@RequestBody UpdateStatusDTO updateStatusDTO) {
        return shoppingService.updateStatus(
                updateStatusDTO.getOrderId(), updateStatusDTO.getStatus());
    }

    //Fetch all orders API endpoint
    @GetMapping(value = "/fetchAllOrders")
    private ResponseEntity fetchAllOrders() {
        return shoppingService.fetchAllOrders();
    }
}
