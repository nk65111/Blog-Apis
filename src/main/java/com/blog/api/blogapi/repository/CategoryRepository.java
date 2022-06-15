package com.blog.api.blogapi.repository;

import com.blog.api.blogapi.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    
}
