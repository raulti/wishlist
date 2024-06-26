package com.raul.infrastructure.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddProductToWishlistRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String name;

}
