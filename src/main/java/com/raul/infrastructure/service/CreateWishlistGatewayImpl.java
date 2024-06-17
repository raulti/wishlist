package com.raul.infrastructure.service;

import com.raul.application.gateway.CreateWishlistGateway;
import com.raul.core.domain.Wishlist;
import com.raul.infrastructure.entity.WishlistEntity;
import com.raul.infrastructure.mapper.WishlistDtoMapper;
import com.raul.infrastructure.repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateWishlistGatewayImpl implements CreateWishlistGateway {

    private final WishlistRepository wishlistRepository;

    public CreateWishlistGatewayImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist execute(Wishlist wishlist) {
        WishlistEntity wishlistEntity = WishlistDtoMapper.toEntity(wishlist);
        WishlistEntity wishlistEntitySaved = wishlistRepository.save(wishlistEntity);
        return WishlistDtoMapper.toDomain(wishlistEntitySaved);
    }

}
