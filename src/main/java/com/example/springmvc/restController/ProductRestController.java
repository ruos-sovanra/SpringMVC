package com.example.springmvc.restController;

import com.example.springmvc.dto.ProductRequest;
import com.example.springmvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private Map<String ,Object> response(Object object, String message, int status)
    {
        HashMap<String,Object> response=new HashMap<>();
        response.put("payload",object);
        response.put("message",message);
        response.put("status",status);
        return response;
    }

    @GetMapping("/getAll")
    public Map<String, Object> getAllProduct(@RequestParam(defaultValue = "") String name) {
        return response(productService.productResponeList(name),"success", HttpStatus.OK.value());
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteProduct(@PathVariable int id) {
        return response(productService.deleteProduct(id), "success", HttpStatus.OK.value());
    }

    @PostMapping("/create")
    public Map<String, Object> createProduct(@RequestBody ProductRequest productRequest) {
        return response(productService.creatProduct(productRequest), "Delete success", HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable int id) {
        return response(productService.getProduct(id), "success", HttpStatus.OK.value());
    }
    @PutMapping("/{id}")
    public Map<String,Object> UpdateProduct(@PathVariable int id,@RequestBody ProductRequest productRequest)
    {
        return response(productService.updateProduct(id,productRequest),"Update success",HttpStatus.OK.value());
    }
}
