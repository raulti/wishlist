package com.raul.infrastructure.service;

import com.raul.application.gateway.FindWishlistByIdGateway;
import com.raul.core.domain.Wishlist;
import com.raul.infrastructure.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindWishlistByIdGatewayImpl implements FindWishlistByIdGateway {

    private final WishlistRepository wishlistRepository;

    public FindWishlistByIdGatewayImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Optional<Wishlist> execute(UUID id) {
        return wishlistRepository.findById(id);
    }
}
