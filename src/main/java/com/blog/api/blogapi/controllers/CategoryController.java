package com.blog.api.blogapi.controllers;

import java.util.List;

import javax.validation.Valid;

import com.blog.api.blogapi.payloads.CategoryDto;
import com.blog.api.blogapi.payloads.ResponderApi;
import com.blog.api.blogapi.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
     
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategoryHandler(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategoryDto=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategoryDto, HttpStatus.CREATED);
    }
    
    @PutMapping("/{catid}")
    public ResponseEntity<CategoryDto> updateCategoryHandler(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catid") Integer catid){
        CategoryDto updateCategoryDto=this.categoryService.updateCategory(categoryDto, catid);
        return new ResponseEntity<>(updateCategoryDto,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{catid}")
    public ResponseEntity<ResponderApi> deleteCategoryHandler(@PathVariable("catid") Integer catid){
        this.categoryService.deleteCategory(catid);
        return new ResponseEntity<>(new ResponderApi("category delete successfully",true),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{catid}")
    public ResponseEntity<CategoryDto> getCategoryHandler(@PathVariable("catid") Integer catid){
        CategoryDto categoryDto= this.categoryService.getCategory(catid);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesHandler(){
        List<CategoryDto> categories= this.categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }
}
