package com.raul.infrastructure.mapper;

import com.raul.core.domain.WishlistFilter;

import java.util.UUID;

public class WishlistFIlterMapper {

    public static WishlistFilter filterRequestToDomain(UUID wishlistId, String productId) {
        WishlistFilter wishlistFilter = new WishlistFilter();
        wishlistFilter.setId(wishlistId);
        wishlistFilter.setProductId(productId);

        return wishlistFilter;
    }
}
