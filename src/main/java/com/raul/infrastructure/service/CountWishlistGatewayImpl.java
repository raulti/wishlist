package com.raul.infrastructure.service;

import com.raul.application.gateway.CountWishlistByCustomerIdGateway;
import com.raul.infrastructure.repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class CountWishlistGatewayImpl implements CountWishlistByCustomerIdGateway {

    private final WishlistRepository wishlistRepository;

    public CountWishlistGatewayImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Long execute(String customerId) {
        return wishlistRepository.countByCustomerId(customerId);
    }

}
