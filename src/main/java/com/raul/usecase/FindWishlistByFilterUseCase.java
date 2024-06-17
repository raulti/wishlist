package com.raul.usecase;

import com.raul.core.domain.Wishlist;
import com.raul.core.domain.WishlistFilter;
import com.raul.core.exception.WishlistNotFoundException;

public interface FindWishlistByFilterUseCase {

    Wishlist execute(WishlistFilter wishlistFilter) throws WishlistNotFoundException;
}
