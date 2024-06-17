package com.raul.application.usecaseimpl;

import com.raul.application.gateway.FindWishlistByFilterGateway;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindWishlistUseCaseImplTest {

    @Mock
    private FindWishlistByFilterGateway findWishlistByFilterGateway;

    @InjectMocks
    private FindWishlistUseCaseImpl findWishlistUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_ShouldReturnFilteredWishlist_WhenWishlistExistsAndContainsProduct() {
        Product product = new Product("123", "Mouse");
        Wishlist wishlist = new Wishlist("456");
        wishlist.setProducts(Stream.of(product).collect(Collectors.toSet()));

        WishlistFilter wishlistFilter = new WishlistFilter(wishlist.getId(), "123");

        when(findWishlistByFilterGateway.execute(wishlistFilter)).thenReturn(Optional.of(wishlist));

        Wishlist result = findWishlistUseCaseImpl.execute(wishlistFilter);

        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertTrue(result.getProducts().contains(product));

        verify(findWishlistByFilterGateway, times(1)).execute(wishlistFilter);
    }

    @Test
    void execute_ShouldThrowWishlistNotFoundException_WhenWishlistDoesNotExist() {
        UUID wishlistId = UUID.randomUUID();
        String productId = "99889";
        WishlistFilter wishlistFilter = new WishlistFilter(wishlistId, productId);

        when(findWishlistByFilterGateway.execute(wishlistFilter)).thenReturn(Optional.empty());

        assertThrows(WishlistNotFoundException.class, () -> {
            findWishlistUseCaseImpl.execute(wishlistFilter);
        });

        verify(findWishlistByFilterGateway, times(1)).execute(wishlistFilter);
    }

    @Test
    void execute_ShouldReturnEmptyWishlist_WhenWishlistExistsButDoesNotContainProduct() {
        String productId = "7788";
        Product product = new Product(productId, "Tela");
        Wishlist wishlist = new Wishlist("1111");
        wishlist.setProducts(Stream.of(product).collect(Collectors.toSet()));

        WishlistFilter wishlistFilter = new WishlistFilter(wishlist.getId(), "888888888");

        when(findWishlistByFilterGateway.execute(wishlistFilter)).thenReturn(Optional.of(wishlist));

        Wishlist result = findWishlistUseCaseImpl.execute(wishlistFilter);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());

        verify(findWishlistByFilterGateway, times(1)).execute(wishlistFilter);
    }
}
