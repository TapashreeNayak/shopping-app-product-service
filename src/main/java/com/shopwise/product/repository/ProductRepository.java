package com.shopwise.product.repository;
import com.shopwise.product.domain.Product;
import com.shopwise.product.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;
public interface ProductRepository extends JpaRepository<Product,Long> {

 Optional<Product> findBySkuIgnoreCase(String sku);
 boolean existsBySkuIgnoreCase(String sku);

 @Query(Constants.FETCH_ALL_PRODUCTS)
 List<Product> searchActive(@Param("category") String category,@Param("search") String search);

}
