package com.shopwise.product.api;
import com.shopwise.product.api.dto.*;
import com.shopwise.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/products")
public class ProductController {

 private final ProductService service;

 public ProductController(ProductService service){
  this.service=service;
 }
 @GetMapping
 public List<ProductResponse> getProducts(@RequestParam(required=false) String category,
                                          @RequestParam(required=false) String search){
           return service.findProducts(category,search);
 }
 @GetMapping("/{id}")
 public ProductResponse getProduct(@PathVariable Long id){
          return service.findById(id);
 }
 @PostMapping @ResponseStatus(HttpStatus.CREATED)
 public ProductResponse create(@Valid @RequestBody ProductRequest request){
           return service.create(request);
 }
 @PutMapping("/{id}")
 public ProductResponse update(@PathVariable Long id,@Valid @RequestBody ProductRequest request){
           return service.update(id,request);
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
 public void deactivate(@PathVariable Long id){
           service.deactivate(id);
 }
}
