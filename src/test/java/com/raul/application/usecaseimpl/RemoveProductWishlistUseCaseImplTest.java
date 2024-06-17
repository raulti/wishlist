package com.raul.application.usecaseimpl;

import com.raul.application.gateway.FindWishlistByFilterGateway;
import com.raul.application.gateway.UpdateWishlistGateway;
import com.raul.core.domain.Product;
import com.raul.core.domain.Wishlist;
import com.raul.core.domain.WishlistFilter;
import com.raul.core.exception.WishlistNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RemoveProductWishlistUseCaseImplTest {

    @Mock
    private UpdateWishlistGateway updateWishlistGateway;

    @Mock
    private FindWishlistByFilterGateway findWishlistByFilterGateway;

    @InjectMocks
    private RemoveProductWishlistUseCaseImpl removeProductWishlistUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ShouldRemoveProductFromWishlist_WhenWishlistExists() {
        String productId = "12";
        Product product = new Product("123", "Teclado");
        Wishlist wishlist = new Wishlist("10001");
        wishlist.addProducts(product);

        WishlistFilter wishlistFilter = new WishlistFilter(wishlist.getId(), productId);

        when(findWishlistByFilterGateway.execute(wishlistFilter)).thenReturn(Optional.of(wishlist));
        when(updateWishlistGateway.execute(any(Wishlist.class))).thenReturn(wishlist);

        removeProductWishlistUseCaseImpl.execute(wishlist.getId(), productId);

        verify(findWishlistByFilterGateway, times(1)).execute(wishlistFilter);
        verify(updateWishlistGateway, times(1)).execute(wishlist);
        assertTrue(wishlist.getProducts().isEmpty());
    }

    @Test
    void execute_ShouldThrowWishlistNotFoundException_WhenWishlistDoesNotExist() {
        UUID wishlistId = UUID.randomUUID();
        String productId = "product-id";
        WishlistFilter wishlistFilter = new WishlistFilter(wishlistId, productId);

        when(findWishlistByFilterGateway.execute(wishlistFilter)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFoundException.class, () -> {
            removeProductWishlistUseCaseImpl.execute(wishlistId, productId);
        });

        verify(findWishlistByFilterGateway, times(1)).execute(wishlistFilter);
        verify(updateWishlistGateway, never()).execute(any(Wishlist.class));
    }
}
