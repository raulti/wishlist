package com.raul.infrastructure.config;

import com.raul.application.gateway.*;
import com.raul.application.usecaseimpl.AddProductWishlistUseCaseImpl;
import com.raul.application.usecaseimpl.CreateWishlistUseCaseImpl;
import com.raul.application.usecaseimpl.FindWishlistUseCaseImpl;
import com.raul.application.usecaseimpl.RemoveProductWishlistUseCaseImpl;
import com.raul.usecase.AddProductWishlistUseCase;
import com.raul.usecase.CreateWishlistUseCase;
import com.raul.usecase.FindWishlistByFilterUseCase;
import com.raul.usecase.RemoveProductWishlistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WishlistInjection {

    @Bean
    CreateWishlistUseCase createWishlist(CreateWishlistGateway createWishlistGateway, CountWishlistByCustomerIdGateway countWishlistByCustomerIdGateway) {
        return new CreateWishlistUseCaseImpl(createWishlistGateway, countWishlistByCustomerIdGateway);
    }

    @Bean
    AddProductWishlistUseCase addProductWishlist(FindWishlistByIdGateway findWishlistByIdGateway, UpdateWishlistGateway updateWishlistGateway) {
        return new AddProductWishlistUseCaseImpl(findWishlistByIdGateway, updateWishlistGateway);
    }

    @Bean
    FindWishlistByFilterUseCase findByFilter(FindWishlistByFilterGateway findWishlistByFilterGateway) {
        return new FindWishlistUseCaseImpl(findWishlistByFilterGateway);
    }

    @Bean
    RemoveProductWishlistUseCase removeProduct(UpdateWishlistGateway updateWishlistGateway, FindWishlistByFilterGateway findWishlistByFilterGateway) {
        return new RemoveProductWishlistUseCaseImpl(updateWishlistGateway, findWishlistByFilterGateway);
    }
}
