package com.example.springmvc.service;

import com.example.springmvc.dto.ProductRequest;
import com.example.springmvc.dto.ProductResponse;
import com.example.springmvc.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> productResponeList(String name);
    ProductResponse deleteProduct(int id);
    ProductResponse creatProduct(ProductRequest productRequest);
    ProductResponse updateProduct(int id,ProductRequest productRequest);
    ProductResponse getProduct(int id);
}
