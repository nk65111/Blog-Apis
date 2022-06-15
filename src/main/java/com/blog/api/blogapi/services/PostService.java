package com.blog.api.blogapi.services;

import java.util.List;

import com.blog.api.blogapi.payloads.PostDto;
import com.blog.api.blogapi.payloads.ResponsePost;

public interface PostService {
    
    PostDto createPost(PostDto postDto,Integer uid,Integer catid);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    ResponsePost getAllPost(Integer pageSize,Integer pageNumber,String sortBy);

    PostDto getPost(Integer postId);

    List<PostDto> getAllPostByUser(Integer userId);

    List<PostDto> getAllPostByCategory(Integer catId);

    List<PostDto> getAllPostByTitle(String title);
    
}
