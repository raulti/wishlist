package com.raul.application.gateway;

import com.raul.core.domain.Wishlist;

public interface CreateWishlistGateway {

    Wishlist execute(Wishlist wishlist);

}