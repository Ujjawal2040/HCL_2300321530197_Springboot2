package com.hcl.hclassessment2.repository;

import com.hcl.hclassessment2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Basic CRUD operations are automatically provided by JpaRepository
}
