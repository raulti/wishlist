package com.raul.application.usecaseimpl;

import com.raul.application.gateway.FindWishlistByFilterGateway;
import com.raul.core.domain.Wishlist;
import com.raul.core.domain.WishlistFilter;
import com.raul.core.exception.WishlistNotFoundException;
import com.raul.usecase.FindWishlistByFilterUseCase;

import java.util.stream.Collectors;

public class FindWishlistUseCaseImpl implements FindWishlistByFilterUseCase {

    private final FindWishlistByFilterGateway findWishlistByFilterGateway;

    public FindWishlistUseCaseImpl(FindWishlistByFilterGateway findWishlistByFilterGateway) {
        this.findWishlistByFilterGateway = findWishlistByFilterGateway;
    }

    @Override
    public Wishlist execute(WishlistFilter wishlistFilter) throws WishlistNotFoundException {
        Wishlist wishlist = findWishlistByFilterGateway.execute(wishlistFilter).orElseThrow(() -> new WishlistNotFoundException("Wishlist not found"));

        wishlist.setProducts(wishlist.getProducts().stream()
                .filter(product -> wishlistFilter.getProductId().equals(product.getId()))
                .collect(Collectors.toSet()));

        return wishlist;
    }
}
