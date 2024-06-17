package com.raul.application.gateway;

import com.raul.core.domain.Product;
import com.raul.core.domain.Wishlist;

import java.util.UUID;

public interface AddProductWishlistGateway {

    Wishlist execute(UUID id, Product product);

}