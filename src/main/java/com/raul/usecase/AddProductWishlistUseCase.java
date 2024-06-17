package com.raul.usecase;

import com.raul.core.domain.Product;
import com.raul.core.domain.Wishlist;
import com.raul.core.exception.WishlistNotFoundException;

import java.util.UUID;

public interface AddProductWishlistUseCase {

    Wishlist execute(UUID id, Product product) throws WishlistNotFoundException;
}
