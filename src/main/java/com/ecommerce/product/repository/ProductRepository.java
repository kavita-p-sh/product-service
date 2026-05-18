package com.ecommerce.product.repository;

import com.ecommerce.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findByPrice(BigDecimal price);

    List<ProductEntity> findByQuantity(Integer quantity);

    boolean existsByName(String name);

    @Modifying
    @Query("""
           UPDATE ProductEntity p
           SET p.quantity = p.quantity - :quantity
           WHERE p.productId = :productId
           AND p.quantity >= :quantity
           """)
    int reduceStockIfAvailable(@Param("productId") Long productId,
                               @Param("quantity") Integer quantity);

    @Modifying
    @Query("""
           UPDATE ProductEntity p
           SET p.quantity = p.quantity + :quantity
           WHERE p.productId = :productId
           """)
    int restoreStock(@Param("productId") Long productId,
                     @Param("quantity") Integer quantity);
}
