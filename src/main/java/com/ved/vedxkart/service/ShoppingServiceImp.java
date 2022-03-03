package com.ved.vedxkart.service;

import com.ved.vedxkart.dto.FetchAllOrdersDTO;
import com.ved.vedxkart.model.Customer;
import com.ved.vedxkart.model.Order;
import com.ved.vedxkart.model.Product;
import com.ved.vedxkart.model.Status;
import com.ved.vedxkart.repository.CustomerRepository;
import com.ved.vedxkart.repository.OrderRepository;
import com.ved.vedxkart.repository.ProductRepository;
import com.ved.vedxkart.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingServiceImp implements ShoppingService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StatusRepository statusRepository;

    public ResponseEntity addCustomer(String customerName, String address, String country) {

        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setAddress(address);
        customer.setCountry(country);
        customerRepository.save(customer);

        return ResponseEntity.ok("Customer Added successfully");
    }

    public ResponseEntity addProduct(String productTitle, String productDescription) {

        Product product = new Product();
        product.setProductTitle(productTitle);
        product.setProductDescription(productDescription);
        productRepository.save(product);

        return ResponseEntity.ok("Product Added Successfully");
    }

    public ResponseEntity placeOrder(Long productId, Long customerId) {

        Order order = new Order();
        order.setProduct(productRepository.findById(productId).get());
        order.setCustomer(customerRepository.findById(customerId).get());
        order.setDate(new Date());
        orderRepository.save(order);

        Status status = new Status();
        status.setOrder(order);
        status.setStatus("Prepared");
        statusRepository.save(status);

        return ResponseEntity.ok("Order Placed Successfully");
    }

    public ResponseEntity updateStatus(Long orderId, String status) {

        Status existingStatus = statusRepository.findByOrder_OrderId(orderId);
        existingStatus.setStatus(status);
        statusRepository.save(existingStatus);

        return ResponseEntity.ok("Your order status updated to " + status);
    }

    public ResponseEntity fetchAllOrders() {

        Iterable<FetchAllOrdersDTO> orders = orderRepository.fetchAllOrders();
        List<FetchAllOrdersDTO> list = Streamable.of(orders).toList();

        return ResponseEntity.ok(list);
    }
}
