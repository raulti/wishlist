package com.raul.application.usecaseimpl;

import com.raul.application.gateway.FindWishlistByIdGateway;
import com.raul.application.gateway.UpdateWishlistGateway;
import com.raul.core.domain.Product;
import com.raul.core.domain.Wishlist;
import com.raul.core.exception.QuantityMaxProductValidationException;
import com.raul.core.exception.WishlistNotFoundException;
import com.raul.usecase.AddProductWishlistUseCase;

import java.util.UUID;

public class AddProductWishlistUseCaseImpl implements AddProductWishlistUseCase {

    private static final int MAX_PRODUCTS = 20;
    private final FindWishlistByIdGateway findWishlistByIdGateway;
    private final UpdateWishlistGateway updateWishlistGateway;

    public AddProductWishlistUseCaseImpl(FindWishlistByIdGateway findWishlistByIdGateway, UpdateWishlistGateway updateWishlistGateway) {
        this.findWishlistByIdGateway = findWishlistByIdGateway;
        this.updateWishlistGateway = updateWishlistGateway;
    }

    @Override
    public Wishlist execute(UUID id, Product product) throws WishlistNotFoundException {
        Wishlist wishlistSaved = findWishlistByIdGateway.execute(id).orElseThrow(
                () -> new WishlistNotFoundException("Wishlist with id " + id + " not found")
        );

        if (wishlistSaved.getProducts().size() >= MAX_PRODUCTS) {
            throw new QuantityMaxProductValidationException("Wishlist cannot have more than 20 products.");
        }

        wishlistSaved.addProducts(product);
        return updateWishlistGateway.execute(wishlistSaved);
    }
}