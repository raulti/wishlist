package com.raul.infrastructure.repository;

import com.raul.core.domain.Wishlist;
import com.raul.infrastructure.entity.WishlistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {
    @Query(value = "{ 'customerId': ?0 }", count = true)
    long countByCustomerId(String customerId);

    Optional<Wishlist> findById(UUID id);

    @Query("{ '_id': ?0, 'products.id':  ?1}")
    Optional<Wishlist> findByIdAndProductId(UUID id, String productId);

}
