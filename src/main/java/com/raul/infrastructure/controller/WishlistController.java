package com.raul.infrastructure.controller;

import com.raul.core.domain.Product;
import com.raul.core.domain.Wishlist;
import com.raul.core.domain.WishlistFilter;
import com.raul.core.exception.WishlistRegistredByCustomerIdException;
import com.raul.infrastructure.dto.request.AddProductToWishlistRequest;
import com.raul.infrastructure.dto.response.AddProductToWishlistResponse;
import com.raul.infrastructure.dto.response.CreateWishlistResponse;
import com.raul.infrastructure.mapper.ProductDtoMapper;
import com.raul.infrastructure.mapper.WishlistDtoMapper;
import com.raul.infrastructure.mapper.WishlistFIlterMapper;
import com.raul.usecase.AddProductWishlistUseCase;
import com.raul.usecase.CreateWishlistUseCase;
import com.raul.usecase.FindWishlistByFilterUseCase;
import com.raul.usecase.RemoveProductWishlistUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

//TODO
// Add datas de criação e update

@RestController
@RequestMapping("/api/wishlist")
@Validated
public class WishlistController {

    private final CreateWishlistUseCase createWishlistUseCase;
    private final AddProductWishlistUseCase addProductWishlistUseCase;
    private final FindWishlistByFilterUseCase findWishlistByFilterUseCase;
    private final RemoveProductWishlistUseCase removeProductWishlistUseCase;

    public WishlistController(CreateWishlistUseCase createWishlistUseCase, AddProductWishlistUseCase addProductWishlistUseCase, FindWishlistByFilterUseCase findWishlistByFilterUseCase, RemoveProductWishlistUseCase removeProductWishlistUseCase) {
        this.createWishlistUseCase = createWishlistUseCase;
        this.addProductWishlistUseCase = addProductWishlistUseCase;
        this.findWishlistByFilterUseCase = findWishlistByFilterUseCase;
        this.removeProductWishlistUseCase = removeProductWishlistUseCase;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CreateWishlistResponse> create(
            @PathVariable @NotBlank @Size(max = 50) String customerId) throws WishlistRegistredByCustomerIdException {

        Wishlist wishlist = createWishlistUseCase.execute(WishlistDtoMapper.createRequestToDomain(customerId));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(WishlistDtoMapper.domainToCreateResponse(wishlist));
    }

    @PatchMapping("/{id}/product")
    public ResponseEntity<AddProductToWishlistResponse> addProduct(@PathVariable UUID id, @RequestBody @Valid AddProductToWishlistRequest request) {
        Product product = ProductDtoMapper.addProductRequestToDomain(request);
        Wishlist wishlist = addProductWishlistUseCase.execute(id, product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(WishlistDtoMapper.domainToAddProducteResponse(wishlist));
    }

    @GetMapping("{id}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable UUID id, @RequestParam(name = "product_id", required = false) String productId) {
        WishlistFilter wishlistFilter = WishlistFIlterMapper.filterRequestToDomain(id, productId);
        Wishlist wishlist = findWishlistByFilterUseCase.execute(wishlistFilter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(wishlist);
    }

    @PatchMapping("/{id}/product/{productId}/remove")
    public ResponseEntity<Wishlist> removeProduct(@PathVariable UUID id, @PathVariable String productId) {
        removeProductWishlistUseCase.execute(id, productId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }
}
