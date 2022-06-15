package com.blog.api.blogapi.services.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.api.blogapi.entities.Category;
import com.blog.api.blogapi.entities.Post;
import com.blog.api.blogapi.entities.User;
import com.blog.api.blogapi.exception.ResourseNotFoundException;
import com.blog.api.blogapi.payloads.PostDto;
import com.blog.api.blogapi.payloads.ResponsePost;
import com.blog.api.blogapi.repository.CategoryRepository;
import com.blog.api.blogapi.repository.PostRepository;
import com.blog.api.blogapi.repository.UserRepository;
import com.blog.api.blogapi.services.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostImpl implements PostService{
     
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    

    @Override
    public PostDto createPost(PostDto postDto, Integer uid, Integer catid) {
       User user= this.userRepository.findById(uid).orElseThrow(()-> new ResourseNotFoundException("User", "User_Id", uid));
       Category category=this.categoryRepository.findById(catid).orElseThrow(()->new ResourseNotFoundException("Category", "Category_ID", catid));

       Post post=this.modelMapper.map(postDto, Post.class);
       if(postDto.getImageName()==null){
           post.setImageName("default.png");
       }

       post.setAddedDate(new Date());
       post.setUser(user);
       post.setCategory(category);
       Post savedPost=this.postRepository.save(post);
       PostDto postDto2= this.modelMapper.map(savedPost, PostDto.class);
       return postDto2;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post", "Post_Id", postId));
        this.postRepository.delete(post);
    }

    @Override
    public ResponsePost getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
        Pageable pageable= PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
        Page<Post> pagePost=this.postRepository.findAll(pageable);
        List<Post> posts=pagePost.getContent();
        List<PostDto> postDtos=new ArrayList<>();
        for(Post post : posts){
            postDtos.add(this.modelMapper.map(post, PostDto.class));
        }
        
        ResponsePost responsePost=new ResponsePost();
        responsePost.setContent(postDtos);
        responsePost.setPageNumber(pagePost.getNumber());
        responsePost.setPageSize(pagePost.getSize());
        responsePost.setTotalPages(pagePost.getTotalPages());
        responsePost.setTotalElements(pagePost.getTotalElements());
        responsePost.setLastPage(pagePost.isLast());
        return responsePost;
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer catId) {
       Category category=this.categoryRepository.findById(catId).orElseThrow(()->new ResourseNotFoundException("Category", "Category_Id", catId));
       
       List<Post> posts=this.postRepository.findByCategory(category);
       List<PostDto> postDtos=new ArrayList<>();
       for(Post post:posts){
           postDtos.add(this.modelMapper.map(post, PostDto.class));
       }
       return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
      User user= this.userRepository.findById(userId).orElseThrow(()->new ResourseNotFoundException("User", "User_Id", userId));

      List<Post> posts=this.postRepository.findByUser(user);
      List<PostDto> postDtos=new ArrayList<>();
       for(Post post:posts){
           postDtos.add(this.modelMapper.map(post, PostDto.class));
       }
       return postDtos;
    }

    @Override
    public PostDto getPost(Integer postId) {
      Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post", "Post_Id", postId));
      return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post", "Post_Id", postId));
        post.setPostContent(postDto.getPostContent());
        post.setPostTitle(postDto.getPostTitle());
        Post savedPost=this.postRepository.save(post);
        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostByTitle(String title) {
       List<Post> posts= this.postRepository.findByTitle("%"+title+"%");
       List<PostDto> postDtos=new ArrayList<>();
       for(Post post:posts){
           postDtos.add(this.modelMapper.map(post, PostDto.class));
       }
       return postDtos;
    }
    
    
}
