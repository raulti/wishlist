package com.raul.core.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Wishlist {
    private UUID id;
    private String customerId;
    private Set<Product> products = new HashSet<>();

    public Wishlist() {
    }

    public Wishlist(String customerId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
    }

    public Wishlist(UUID id, String customerId, Set<Product> products) {
        this.id = id;
        this.customerId = customerId;
        this.products = products;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void addProducts(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return Objects.equals(customerId, wishlist.customerId) && Objects.equals(products, wishlist.products) && Objects.equals(id, wishlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, products, id);
    }
}
