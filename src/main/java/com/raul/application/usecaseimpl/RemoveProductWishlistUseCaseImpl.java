package com.raul.application.usecaseimpl;

import com.raul.application.gateway.FindWishlistByFilterGateway;
import com.raul.application.gateway.UpdateWishlistGateway;
import com.raul.core.domain.Wishlist;
import com.raul.core.domain.WishlistFilter;
import com.raul.core.exception.WishlistNotFoundException;
import com.raul.usecase.RemoveProductWishlistUseCase;

import java.util.UUID;

public class RemoveProductWishlistUseCaseImpl implements RemoveProductWishlistUseCase {

    private final UpdateWishlistGateway updateWishlistGateway;
    private final FindWishlistByFilterGateway findWishlistByFilterGateway;

    public RemoveProductWishlistUseCaseImpl(UpdateWishlistGateway updateWishlistGateway, FindWishlistByFilterGateway findWishlistByFilterGateway) {
        this.updateWishlistGateway = updateWishlistGateway;
        this.findWishlistByFilterGateway = findWishlistByFilterGateway;
    }

    @Override
    public void execute(UUID wishlistId, String productId) throws WishlistNotFoundException {
        WishlistFilter wishlistFilter = new WishlistFilter(wishlistId, productId);
        Wishlist wishlist = findWishlistByFilterGateway.execute(wishlistFilter).orElseThrow(() -> new WishlistNotFoundException("Wishlist not found"));

        wishlist.removeProduct(wishlist.getProducts().iterator().next());

        updateWishlistGateway.execute(wishlist);
    }
}
