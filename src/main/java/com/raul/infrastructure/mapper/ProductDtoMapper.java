package com.raul.infrastructure.mapper;

import com.raul.core.domain.Product;
import com.raul.infrastructure.dto.request.AddProductToWishlistRequest;
import com.raul.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductDtoMapper {

    public static Product toDomain(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        Product model = new Product();
        model.setId(productEntity.getId());
        model.setName(productEntity.getName());

        return model;
    }

    public static Set<Product> toDomainList(Set<ProductEntity> productsEntity) {
        if (productsEntity == null) {
            return null;
        }

        return productsEntity.stream()
                .map(ProductDtoMapper::toDomain)
                .collect(Collectors.toSet());
    }

    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());

        return productEntity;
    }

    public static Set<ProductEntity> toEntityList(Set<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(ProductDtoMapper::toEntity)
                .collect(Collectors.toSet());
    }

    public static Product addProductRequestToDomain(AddProductToWishlistRequest productRequest) {
        if (productRequest == null) {
            return null;
        }

        Product model = new Product();
        model.setId(productRequest.getId());
        model.setName(productRequest.getName());

        return model;
    }
}
