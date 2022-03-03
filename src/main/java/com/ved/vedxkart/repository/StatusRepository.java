package com.ved.vedxkart.repository;

import com.ved.vedxkart.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface StatusRepository extends CrudRepository<Status, Serializable> {

    public Status findByOrder_OrderId(Long orderId);
}
