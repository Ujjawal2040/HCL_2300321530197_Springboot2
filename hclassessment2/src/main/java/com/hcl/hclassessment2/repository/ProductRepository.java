package com.hcl.hclassessment2.repository;

import com.hcl.hclassessment2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query method to find products by their category field
    List<Product> findByCategoryIgnoreCase(String category);
}
