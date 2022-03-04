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
import com.ved.vedxkart.utils.HttpConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        //Creating the new customer
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setAddress(address);
        customer.setCountry(country);
        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.OK).body(HttpConstants.CUSTOMER_ADDED);
    }

    public ResponseEntity addProduct(String productTitle, String productDescription) {

        //Fetching If there already exists the same product or not
        Product existingProduct = productRepository.findByProductTitleAndProductDescription(
                productTitle, productDescription);
        //If product already exists
        if (existingProduct != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpConstants.PRODUCT_ALREADY_EXIST);

        //Adding new product
        else {
            Product product = new Product();
            product.setProductTitle(productTitle);
            product.setProductDescription(productDescription);
            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(HttpConstants.PRODUCT_ADDED);
        }
    }

    public ResponseEntity placeOrder(Long productId, Long customerId) {

        //Fetching product with productId and customer with customerId
        Optional<Product> product = productRepository.findById(productId);
        Optional<Customer> customer = customerRepository.findById(customerId);

        //If product doesn't exist with above productId
        if (product.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpConstants.INVALID_PRODUCT_ID);

        //If customer doesn't exist with above customerId
        else if (customer.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpConstants.INVALID_CUSTOMER_ID);

        //Placing the order with productId and customerId
        else {
        Order order = new Order();
        order.setProduct(product.get());
        order.setCustomer(customer.get());
        order.setDate(new Date());
        orderRepository.save(order);

        //Setting the status of the order to prepared
        Status status = new Status();
        status.setOrder(order);
        status.setStatus(HttpConstants.ORDER_PREPARED);
        statusRepository.save(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(HttpConstants.ORDER_PLACED);
        }
    }

    public ResponseEntity updateStatus(Long orderId, String status) {

        //Fetching existing order status with orderId
        Status existingStatus = statusRepository.findByOrder_OrderId(orderId);

        //If order doesn't exist with above orderId
        if (existingStatus == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpConstants.ORDER_NOT_FOUND);

        else if (existingStatus.getStatus().equals(status))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpConstants.STATUS_ALREADY_UPDATED + status);
        //Updating status of the order
        else {
            existingStatus.setStatus(status);
            statusRepository.save(existingStatus);

            return ResponseEntity.status(HttpStatus.CREATED).body(HttpConstants.STATUS_UPDATED + status);
        }
    }

    public ResponseEntity fetchAllOrders() {

        //Fetching all orders
        Iterable<FetchAllOrdersDTO> orders = orderRepository.fetchAllOrders();
        List<FetchAllOrdersDTO> list = Streamable.of(orders).toList();

       //If there doesn't exist any order
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(HttpConstants.NO_ORDER_PLACED);

        //Sending all orders in the response body
        else
            return ResponseEntity.status(HttpStatus.OK).body(list);
   }
}
