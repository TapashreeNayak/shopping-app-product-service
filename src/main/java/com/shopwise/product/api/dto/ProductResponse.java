package com.shopwise.product.api.dto;
import com.shopwise.product.domain.Product;
import java.math.BigDecimal;
public record ProductResponse(Long id,String sku,String name,String category,
                              String description,BigDecimal price,BigDecimal rating,
                              String badge,String imageUrl,boolean active) {
 public static ProductResponse from(Product p){
  return new ProductResponse(p.getId(),p.getSku(),p.getName(),p.getCategory(),
          p.getDescription(),p.getPrice(),p.getRating(),p.getBadge(),p.getImageUrl(),
          p.isActive());}
}
