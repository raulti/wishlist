package com.raul.infrastructure.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateWishlistRequest {

    @NotBlank
    @Size(max = 50)
    private String customerId;

}
