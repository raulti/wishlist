package com.raul.infrastructure.dto.response;

import java.util.UUID;

public record CreateWishlistResponse(UUID id, String customerId) {
}
