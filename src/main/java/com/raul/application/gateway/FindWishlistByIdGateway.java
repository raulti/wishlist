package com.raul.application.gateway;

import com.raul.core.domain.Wishlist;

import java.util.Optional;
import java.util.UUID;

public interface FindWishlistByIdGateway {

    Optional<Wishlist> execute(UUID id);

}