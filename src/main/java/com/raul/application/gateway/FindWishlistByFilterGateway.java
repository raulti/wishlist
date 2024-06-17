package com.raul.application.gateway;

import com.raul.core.domain.Wishlist;
import com.raul.core.domain.WishlistFilter;

import java.util.Optional;

public interface FindWishlistByFilterGateway {

    Optional<Wishlist> execute(WishlistFilter wishlistFilter);

}