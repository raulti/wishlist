package com.raul.infrastructure.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddProductToWishlistRequest {

    @NotNull(message = "Product cannot be null")
    private String id;

    @NotNull(message = "Product cannot be null")
    private String name;

}
