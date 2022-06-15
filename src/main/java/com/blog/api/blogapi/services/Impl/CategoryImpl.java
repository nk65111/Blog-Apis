package com.blog.api.blogapi.services.Impl;

import java.util.ArrayList;
import java.util.List;

import com.blog.api.blogapi.entities.Category;
import com.blog.api.blogapi.exception.ResourseNotFoundException;
import com.blog.api.blogapi.payloads.CategoryDto;
import com.blog.api.blogapi.repository.CategoryRepository;
import com.blog.api.blogapi.services.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto, Category.class);
        Category savedUser= this.categoryRepository.save(category);
        return this.modelMapper.map(savedUser, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category= this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category", "Category_Id", categoryId));
        this.categoryRepository.delete(category); 
    }

    @Override
    public List<CategoryDto> getAllCategory() {
       List<Category> categories= this.categoryRepository.findAll();
       List<CategoryDto> categoryDtos=new ArrayList<>();
       for(Category category:categories){
           categoryDtos.add(this.modelMapper.map(category, CategoryDto.class));
       }
       return categoryDtos;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category", "Category_ID", categoryId));
        
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category", "Category_ID", categoryId));

        //update category details
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category savedCategory= this.categoryRepository.save(category);

        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }
    
}
