package com.blog.api.blogapi.repository;

import com.blog.api.blogapi.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer>{
    
}
