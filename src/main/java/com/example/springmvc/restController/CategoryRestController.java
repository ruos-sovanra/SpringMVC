package com.example.springmvc.restController;

import com.example.springmvc.dto.CategoryRequest;
import com.example.springmvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {
    private final CategoryService categoryService;
    private Map<String,Object> response(Object objecr,String message,int status)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("data",objecr);
        response.put("message",message);
        response.put("status", status);
        return response;
    }
    @GetMapping("/getAll")
    public Map<String,Object> getAllCategory(@RequestParam (defaultValue = "") String name){
        return response(categoryService.getAllCategory(name),"Successfully",HttpStatus.OK.value());
    }
    @DeleteMapping("/{id}")
    public Map<String,Object> DeleteCategory(@PathVariable int id)
    {
        categoryService.deleteCategory(id);
        return response(new ArrayList<>(),"Delete Successfully",HttpStatus.OK.value());
    }
    @PutMapping("/{id}")
    public Map<String,Object> UpdateCategory(@PathVariable int id, CategoryRequest categoryRequest)
    {
        return response(categoryService.updateCategory(id, categoryRequest),"Update Successfully",HttpStatus.OK.value());
    }
    @PostMapping("/create")
    public Map<String,Object> CreateCategory(@RequestBody CategoryRequest categoryRequest)
    {
        return response(categoryService.createCategory(categoryRequest),"Insert Successfully",HttpStatus.OK.value());
    }
    @GetMapping("/{id}")
    public Map<String,Object> CategoryById(@PathVariable int id)
    {
        return response(categoryService.getCategory(id),"Successfully", HttpStatus.OK.value());
    }
}
