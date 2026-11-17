package com.shopwise.product.service;
import com.shopwise.product.api.dto.*;
import com.shopwise.product.domain.Product;
import com.shopwise.product.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class ProductService {

 private final ProductRepository repository;
 public ProductService(ProductRepository repository){this.repository=repository;}

 @Transactional(readOnly=true)
 public List<ProductResponse> findProducts(String category,String search){
  return repository.searchActive(normalize(category),normalize(search)).stream().map(ProductResponse::from).toList();
 }

 @Transactional(readOnly=true)
 public ProductResponse findById(Long id){
  return ProductResponse.from(repository.findById(id).filter(Product::isActive).orElseThrow(()->new ProductNotFoundException(id)));
 }
 public ProductResponse create(ProductRequest r){
  if(repository.existsBySkuIgnoreCase(r.sku()))
   throw new DuplicateSkuException(r.sku());
  Product p=new Product();apply(p,r);
  try{
   return ProductResponse.from(repository.save(p));
  }catch(DataIntegrityViolationException e){
   throw new DuplicateSkuException(r.sku());
  }
 }
 public ProductResponse update(Long id,ProductRequest r){
  Product p=repository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
  repository.findBySkuIgnoreCase(r.sku())
          .filter(x->!x.getId().equals(id))
          .ifPresent(x->{
           throw new DuplicateSkuException(r.sku());
          });
  apply(p,r);
  return ProductResponse.from(repository.save(p));
 }
 public void deactivate(Long id){
  Product p=repository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
  p.setActive(false);
  repository.save(p);
 }
 private void apply(Product p,ProductRequest r){
  p.setSku(r.sku().trim());
  p.setName(r.name().trim());
  p.setCategory(r.category().trim());
  p.setDescription(r.description());
  p.setPrice(r.price());
  p.setRating(r.rating());
  p.setBadge(r.badge());
  p.setImageUrl(r.imageUrl());
  p.setActive(r.active()==null||r.active());
 }
 private String normalize(String s){
  return s==null||s.isBlank()?null:s.trim();
 }
}
