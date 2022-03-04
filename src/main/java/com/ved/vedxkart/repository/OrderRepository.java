package com.ved.vedxkart.repository;


import com.ved.vedxkart.dto.FetchAllOrdersDTO;
import com.ved.vedxkart.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Serializable> {

    //Creating query to fetch order details and passing through FetchAllOrderDTO
    @Query("select new com.ved.vedxkart.dto.FetchAllOrdersDTO (o.orderId," +
            "o.customer.customerName,o.customer.country,o.customer.address," +
            "o.product.productTitle,o.product.productDescription,o.date," +
            "s.status) from Order o join Status s on o.orderId=s.order.orderId")
    public List<FetchAllOrdersDTO> fetchAllOrders();
}
