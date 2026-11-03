package com.hcl.hclassessment2.repository;

import com.hcl.hclassessment2.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCustomerId(Long customerId);

    Optional<CartItem> findByCustomerIdAndProductId(Long customerId, Long productId);

    @Transactional
    void deleteByCustomerId(Long customerId);

    @Transactional
    void deleteByCustomerIdAndProductId(Long customerId, Long productId);
}
