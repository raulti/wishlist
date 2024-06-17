package com.raul.application.usecaseimpl;

import com.raul.application.gateway.FindWishlistByIdGateway;
import com.raul.application.gateway.UpdateWishlistGateway;
import com.raul.core.domain.Product;
import com.raul.core.domain.Wishlist;
import com.raul.core.exception.QuantityMaxProductValidationException;
import com.raul.core.exception.WishlistNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddProductWishlistUseCaseImplTest {

    @Mock
    private FindWishlistByIdGateway findWishlistByIdGateway;

    @Mock
    private UpdateWishlistGateway updateWishlistGateway;

    @InjectMocks
    private AddProductWishlistUseCaseImpl addProductWishlistUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ShouldAddProductToWishlist_WhenWishlistExistsAndHasLessThanMaxProducts() {
        Product product = new Product("123", "Tela");
        Wishlist wishlist = new Wishlist("99999");

        when(findWishlistByIdGateway.execute(wishlist.getId())).thenReturn(Optional.of(wishlist));
        when(updateWishlistGateway.execute(any(Wishlist.class))).thenReturn(wishlist);

        Wishlist updatedWishlist = addProductWishlistUseCaseImpl.execute(wishlist.getId(), product);

        assertNotNull(updatedWishlist);
        assertEquals(1, updatedWishlist.getProducts().size());
        assertEquals(product, updatedWishlist.getProducts().iterator().next());

        verify(findWishlistByIdGateway, times(1)).execute(wishlist.getId());
        verify(updateWishlistGateway, times(1)).execute(any(Wishlist.class));
    }

    @Test
    void execute_ShouldThrowWishlistNotFoundException_WhenWishlistDoesNotExist() {
        UUID wishlistId = UUID.randomUUID();
        Product product = new Product("123", "Tela");

        when(findWishlistByIdGateway.execute(wishlistId)).thenReturn(Optional.empty());

        WishlistNotFoundException exception = assertThrows(WishlistNotFoundException.class, () -> {
            addProductWishlistUseCaseImpl.execute(wishlistId, product);
        });

        assertEquals("Wishlist with id " + wishlistId + " not found", exception.getMessage());

        verify(findWishlistByIdGateway, times(1)).execute(wishlistId);
        verify(updateWishlistGateway, never()).execute(any(Wishlist.class));
    }

    @Test
    void execute_ShouldThrowQuantityMaxProductValidationException_WhenWishlistHasMaxProducts() {
        Product product = new Product("123", "Tela");
        Wishlist wishlist = new Wishlist("888888");

        for (int i = 0; i < 20; i++) {
            wishlist.addProducts(new Product("1234" + i, "Tela" + i));
        }

        when(findWishlistByIdGateway.execute(wishlist.getId())).thenReturn(Optional.of(wishlist));

        QuantityMaxProductValidationException exception = assertThrows(QuantityMaxProductValidationException.class, () -> {
            addProductWishlistUseCaseImpl.execute(wishlist.getId(), product);
        });

        assertEquals("Wishlist cannot have more than 20 products.", exception.getMessage());

        verify(findWishlistByIdGateway, times(1)).execute(wishlist.getId());
        verify(updateWishlistGateway, never()).execute(any(Wishlist.class));
    }
}
