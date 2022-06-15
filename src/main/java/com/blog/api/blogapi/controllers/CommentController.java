package com.blog.api.blogapi.controllers;

import com.blog.api.blogapi.payloads.CommentDto;
import com.blog.api.blogapi.payloads.ResponderApi;
import com.blog.api.blogapi.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/")
    public ResponseEntity<CommentDto> createCommentHandler(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId){
       CommentDto savedCommentDto= this.commentService.createComment(commentDto, postId);
       return new ResponseEntity<CommentDto>(savedCommentDto, HttpStatus.CREATED);
    }


    @DeleteMapping("/{cId}")
    public ResponseEntity<ResponderApi> deleteCommentHandler(@PathVariable("cId") Integer commentId){
          this.commentService.deleteComment(commentId);
          return new ResponseEntity<>(new ResponderApi("Coment deleted succesfully",true),HttpStatus.OK);
    }
}
