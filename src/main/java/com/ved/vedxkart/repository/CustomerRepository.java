package com.ved.vedxkart.repository;

import com.ved.vedxkart.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Serializable> {
}
