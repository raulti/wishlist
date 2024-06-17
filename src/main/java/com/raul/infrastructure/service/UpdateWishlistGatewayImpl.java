package com.raul.infrastructure.service;

import com.raul.application.gateway.UpdateWishlistGateway;
import com.raul.core.domain.Wishlist;
import com.raul.infrastructure.entity.WishlistEntity;
import com.raul.infrastructure.mapper.WishlistDtoMapper;
import com.raul.infrastructure.repository.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateWishlistGatewayImpl implements UpdateWishlistGateway {

    private final WishlistRepository wishlistRepository;

    public UpdateWishlistGatewayImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist execute(Wishlist wishlist) {
        WishlistEntity wishlistEntity = WishlistDtoMapper.toEntity(wishlist);
        WishlistEntity wishlistEntitySaved = wishlistRepository.save(wishlistEntity);
        return WishlistDtoMapper.toDomain(wishlistEntitySaved);
    }
}
