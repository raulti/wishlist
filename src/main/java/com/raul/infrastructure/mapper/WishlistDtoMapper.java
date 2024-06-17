package com.raul.infrastructure.mapper;

import com.raul.core.domain.Product;
import com.raul.core.domain.Wishlist;
import com.raul.infrastructure.dto.response.AddProductToWishlistResponse;
import com.raul.infrastructure.dto.response.CreateWishlistResponse;
import com.raul.infrastructure.entity.WishlistEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class WishlistDtoMapper {

    public static Wishlist createRequestToDomain(String customerId) {
        return new Wishlist(customerId);
    }

    public static Wishlist toDomain(WishlistEntity wishlistEntity) {
        Set<Product> products = ProductDtoMapper.toDomainList(wishlistEntity.getProducts());

        Wishlist wishlist = new Wishlist();
        wishlist.setId(wishlistEntity.getId());
        wishlist.setCustomerId(wishlistEntity.getCustomerId());
        wishlist.setProducts(products);

        return wishlist;
    }

    public static CreateWishlistResponse domainToCreateResponse(Wishlist wishlist) {
        return new CreateWishlistResponse(wishlist.getId(), wishlist.getCustomerId());
    }

    public static AddProductToWishlistResponse domainToAddProducteResponse(Wishlist wishlist) {
        return new AddProductToWishlistResponse(wishlist);
    }

    public static WishlistEntity toEntity(Wishlist wishlist) {
        WishlistEntity wishlistEntity = new WishlistEntity();

        wishlistEntity.setCustomerId(wishlist.getCustomerId());
        wishlistEntity.setId(wishlist.getId());
        wishlistEntity.setProducts(ProductDtoMapper.toEntityList(wishlist.getProducts()));

        return wishlistEntity;
    }

}
