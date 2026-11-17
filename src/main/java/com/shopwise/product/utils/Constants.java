package com.shopwise.product.utils;

public class Constants {

    public static final String FETCH_ALL_PRODUCTS = "select p from Product p where p.active=true and " +
            "(:category is null or lower(p.category)=lower(:category)) " +
            "and (:search is null or lower(p.name) " +
            "like lower(concat('%',:search,'%')) or lower(coalesce(p.description,'')) " +
            "like lower(concat('%',:search,'%'))) order by p.id desc";
}
