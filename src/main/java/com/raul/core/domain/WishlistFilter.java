package com.raul.core.domain;


import java.util.Objects;
import java.util.UUID;

public class WishlistFilter {
    private UUID id;
    private String productId;

    public WishlistFilter() {
    }

    public WishlistFilter(UUID id, String productId) {
        this.id = id;
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishlistFilter that = (WishlistFilter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}