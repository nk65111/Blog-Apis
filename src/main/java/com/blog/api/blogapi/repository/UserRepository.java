package com.blog.api.blogapi.repository;

import com.blog.api.blogapi.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer>{
    
}
