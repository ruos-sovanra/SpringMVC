package com.example.springmvc.service;

import com.example.springmvc.dto.ProductRequest;
import com.example.springmvc.dto.ProductResponse;
import com.example.springmvc.model.Product;
import com.example.springmvc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private ProductResponse MapToProductRespone(Product product)
    {
        return ProductResponse.builder().id(product.getId()).title(product.getTitle())
                .description(product.getDescription()).price(product.getPrice())
                .imageUrl(product.getImageUrl()).build();
    }
    private Product MapProductRequest(ProductRequest productRequest)
    {
        return Product.builder().title(productRequest.title())
                .description(productRequest.description())
                .price(productRequest.price())
                .imageUrl(productRequest.imageUrl()).build();
    }
    @Override
    public List<ProductResponse> productResponeList(String name) {
        var product = productRepository.getAllProduct();
        if (!name.isEmpty()){
            product.stream().filter(
                    pro-> pro.getTitle().toLowerCase().contains(name.toLowerCase())
            ).toList();
        }
        return  productRepository.getAllProduct().stream()
                .map(this::MapToProductRespone).toList();
    }
    @Override
    public ProductResponse deleteProduct(int id) {
        Product product=productRepository.getAllProduct().stream()
                .filter(pro->pro.getId()==id).findFirst()
                .orElseThrow(()->new RuntimeException("Product not found"));
        productRepository.getAllProduct().remove(product);
        return MapToProductRespone(product);
    }
    @Override
    public ProductResponse creatProduct(ProductRequest productRequest) {
        var newProduct=MapProductRequest(productRequest);
        var maxId=productRepository.getAllProduct()
                .stream().max(Comparator.comparing(Product::getId))
                .map(Product::getId);
        System.out.println("max id is="+maxId);
        newProduct.setId(maxId.orElse(0)+1);
        productRepository.addProduct(newProduct);
        return MapToProductRespone(newProduct);
    }
    @Override
    public ProductResponse updateProduct(int id,ProductRequest productRequest) {
        Product product=productRepository.getAllProduct().stream()
                .filter(pro->pro.getId()==id).findFirst()
                .orElseThrow(()->new RuntimeException("Product not found"));
        product.setTitle(productRequest.title());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setImageUrl(productRequest.imageUrl());
        return MapToProductRespone(product);
    }
    @Override
    public ProductResponse getProduct(int id) {
        Product products= productRepository.getAllProduct().stream()
                .filter(product->product.getId()==id)
                .findFirst()
                .orElseThrow(()->
                        new RuntimeException("Product not found"));
        return MapToProductRespone(products);
    }
}
