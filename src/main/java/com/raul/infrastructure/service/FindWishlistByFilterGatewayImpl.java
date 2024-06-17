package com.raul.infrastructure.service;

import com.raul.application.gateway.FindWishlistByFilterGateway;
import com.raul.core.domain.Wishlist;
import com.raul.core.domain.WishlistFilter;
import com.raul.infrastructure.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindWishlistByFilterGatewayImpl implements FindWishlistByFilterGateway {

    private final WishlistRepository wishlistRepository;

    public FindWishlistByFilterGatewayImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Optional<Wishlist> execute(WishlistFilter wishlistFilter) {
        Optional<Wishlist> wishlistOptional;
        if (wishlistFilter.getProductId() == null || wishlistFilter.getProductId().isEmpty()) {
            return wishlistRepository.findById(wishlistFilter.getId());
        }

        return wishlistRepository.findByIdAndProductId(wishlistFilter.getId(), wishlistFilter.getProductId());
    }
}
