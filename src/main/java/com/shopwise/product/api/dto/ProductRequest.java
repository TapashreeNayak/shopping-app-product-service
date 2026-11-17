package com.shopwise.product.api.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ProductRequest(
 @NotBlank @Size(max=50) String sku,
 @NotBlank @Size(max=200) String name,
 @NotBlank @Size(max=80) String category,
 @Size(max=1000) String description,
 @NotNull @DecimalMin("0.01") @Digits(integer=10,fraction=2) BigDecimal price,
 @DecimalMin("0.0") @DecimalMax("5.0") @Digits(integer=1,fraction=1) BigDecimal rating,
 @Size(max=40) String badge,
 @Size(max=1000) String imageUrl,
 Boolean active
) {}
