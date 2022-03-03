package com.ved.vedxkart.repository;

import com.ved.vedxkart.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProductRepository extends CrudRepository<Product, Serializable> {
}
