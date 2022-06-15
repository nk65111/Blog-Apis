package com.blog.api.blogapi.controllers;

import java.util.List;

import com.blog.api.blogapi.config.AppConstant;
import com.blog.api.blogapi.payloads.PostDto;
import com.blog.api.blogapi.payloads.ResponderApi;
import com.blog.api.blogapi.payloads.ResponsePost;
import com.blog.api.blogapi.services.PostService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    private PostService postService;

    @PostMapping("/user/{uid}/categories/{catid}/posts/")
    public ResponseEntity<PostDto> createPostHandler(@RequestBody PostDto postDto,@PathVariable("uid") Integer uid,@PathVariable("catid") Integer catid){
       PostDto savePostDto= this.postService.createPost(postDto, uid, catid);
       return  new ResponseEntity<>(savePostDto,HttpStatus.CREATED);
    }
    
    @PutMapping("/posts/{pid}")
    public ResponseEntity<PostDto> updatePostHandler(@RequestBody PostDto postDto,@PathVariable("pid") Integer pid){
       PostDto updatedPostDto= this.postService.updatePost(postDto, pid);
       return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{pid}")
    public ResponseEntity<ResponderApi> deletePostHandler(@PathVariable Integer pid){
        this.postService.deletePost(pid);
        return new ResponseEntity<>(new ResponderApi("post delete succesfully", true),HttpStatus.OK);
    }

    @GetMapping("/posts/")
    public ResponseEntity<ResponsePost> getAllPostHandler( @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER ,required = false)Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE ,required = false) Integer pageSize,@RequestParam(value = "sortBy", defaultValue = AppConstant.SHORT_BY , required = false ) String sortBy){

        ResponsePost responsePost= this.postService.getAllPost(pageNumber,pageSize,sortBy);
        return new ResponseEntity<>(responsePost,HttpStatus.OK);
    }

    @GetMapping("/posts/{pid}")
    public ResponseEntity<PostDto> getPostHandler(@PathVariable("pid") Integer pid){
       PostDto postDto= this.postService.getPost(pid);
       return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/user/{userid}/posts/")
    public ResponseEntity<List<PostDto>> getPostByUserHandler(@PathVariable("userid") Integer userId){
        List<PostDto> postDtos=this.postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/categories/{catId}/posts/")
    public ResponseEntity<List<PostDto>> getPostByCategoryHandler(@PathVariable("catId") Integer catId){
        List<PostDto> postDtos=this.postService.getAllPostByCategory(catId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/posts/search/{title}")
    public ResponseEntity<List<PostDto>> getAllPostByTitleHandler(@PathVariable("title") String title){
       List<PostDto> postDtos= this.postService.getAllPostByTitle(title);
       return  new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
