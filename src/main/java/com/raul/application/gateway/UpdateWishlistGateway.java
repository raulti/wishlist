package com.raul.application.gateway;

import com.raul.core.domain.Wishlist;

public interface UpdateWishlistGateway {

    Wishlist execute(Wishlist wishlist);

}