package com.raul.infrastructure.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Document(collection = "wishlists")
public class WishlistEntity {

    @Id
    private UUID id;

    @NotBlank
    @Indexed(unique = true)
    private String customerId;

    @Valid
    private Set<ProductEntity> products = new HashSet<>();

    public void addProduct(@Valid ProductEntity product) {
        this.products.add(product);
    }

    public void removeProduct(String productId) {
        this.products.removeIf(product -> product.getId().equals(productId));
    }

}
