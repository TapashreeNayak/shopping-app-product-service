package com.shopwise.product.domain;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Entity @Table(name="PRODUCTS")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="ID") private Long id;
 @Column(name="SKU",nullable=false,unique=true,length=50) private String sku;
 @Column(name="NAME",nullable=false,length=200) private String name;
 @Column(name="CATEGORY",nullable=false,length=80) private String category;
 @Column(name="DESCRIPTION",length=1000) private String description;
 @Column(name="PRICE",nullable=false,precision=12,scale=2) private BigDecimal price;
 @Column(name="RATING",precision=2,scale=1) private BigDecimal rating;
 @Column(name="BADGE",length=40) private String badge;
 @Column(name="IMAGE_URL",length=1000) private String imageUrl;
 @Column(name="ACTIVE",nullable=false) private boolean active=true;
 @Column(name="CREATED_AT",nullable=false) private OffsetDateTime createdAt;
 @Column(name="UPDATED_AT",nullable=false) private OffsetDateTime updatedAt;

 @PrePersist
 void onCreate(){
  OffsetDateTime now=OffsetDateTime.now();createdAt=now;updatedAt=now;}
 @PreUpdate
 void onUpdate(){ updatedAt=OffsetDateTime.now();}

 public Long getId(){return id;}
 public String getSku(){return sku;}
 public String getName(){return name;}
 public String getCategory(){return category;}
 public String getDescription(){return description;}
 public BigDecimal getPrice(){return price;}
 public BigDecimal getRating(){return rating;}
 public String getBadge(){return badge;}
 public String getImageUrl(){return imageUrl;}
 public boolean isActive(){return active;}
 public OffsetDateTime getCreatedAt(){return createdAt;}
 public OffsetDateTime getUpdatedAt(){return updatedAt;}
 public void setSku(String v){sku=v;}
 public void setName(String v){name=v;}
 public void setCategory(String v){category=v;}
 public void setDescription(String v){description=v;}
 public void setPrice(BigDecimal v){price=v;}
 public void setRating(BigDecimal v){rating=v;}
 public void setBadge(String v){badge=v;}
 public void setImageUrl(String v){imageUrl=v;}
 public void setActive(boolean v){active=v;}
}
