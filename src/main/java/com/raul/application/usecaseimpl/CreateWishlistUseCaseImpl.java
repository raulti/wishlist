package com.raul.application.usecaseimpl;

import com.raul.application.gateway.CountWishlistByCustomerIdGateway;
import com.raul.application.gateway.CreateWishlistGateway;
import com.raul.core.domain.Wishlist;
import com.raul.core.exception.WishlistRegistredByCustomerIdException;
import com.raul.usecase.CreateWishlistUseCase;

public class CreateWishlistUseCaseImpl implements CreateWishlistUseCase {

    private final CreateWishlistGateway createWishlistGateway;
    private final CountWishlistByCustomerIdGateway countWishlistByCustomerIdGateway;

    public CreateWishlistUseCaseImpl(CreateWishlistGateway createWishlistGateway, CountWishlistByCustomerIdGateway countWishlistByCustomerIdGateway) {
        this.createWishlistGateway = createWishlistGateway;
        this.countWishlistByCustomerIdGateway = countWishlistByCustomerIdGateway;
    }

    @Override
    public Wishlist execute(Wishlist wishlist) throws WishlistRegistredByCustomerIdException {
        Long countWishlist = countWishlistByCustomerIdGateway.execute((wishlist.getCustomerId()));
        if (countWishlist > 0) {
            throw new WishlistRegistredByCustomerIdException("Wishlist already registered for this client");
        }
        return createWishlistGateway.execute(wishlist);
    }
}
