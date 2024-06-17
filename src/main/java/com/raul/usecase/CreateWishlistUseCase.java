package com.raul.usecase;

import com.raul.core.domain.Wishlist;
import com.raul.core.exception.WishlistRegistredByCustomerIdException;

public interface CreateWishlistUseCase {

    Wishlist execute(Wishlist wishlist) throws WishlistRegistredByCustomerIdException;
}
