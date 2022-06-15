package com.blog.api.blogapi.repository;

import java.util.List;

import com.blog.api.blogapi.entities.Category;
import com.blog.api.blogapi.entities.Post;
import com.blog.api.blogapi.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post,Integer> {
    
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    @Query("select p from Post p where p.postTitle like :key")
    List<Post> findByTitle(@Param("key") String title);
}
