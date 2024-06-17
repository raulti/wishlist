package com.raul.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data()
public class ProductEntity {

    @Id
    private String id;

    @NotBlank
    private String name;

}
