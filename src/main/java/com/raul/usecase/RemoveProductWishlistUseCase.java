package com.raul.usecase;

import com.raul.core.exception.WishlistNotFoundException;

import java.util.UUID;

public interface RemoveProductWishlistUseCase {

    void execute(UUID wishlistId, String productId) throws WishlistNotFoundException;
}
