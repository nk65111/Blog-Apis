package com.blog.api.blogapi.services.Impl;

import com.blog.api.blogapi.entities.Comment;
import com.blog.api.blogapi.entities.Post;
import com.blog.api.blogapi.exception.ResourseNotFoundException;
import com.blog.api.blogapi.payloads.CommentDto;
import com.blog.api.blogapi.repository.CommentRepository;
import com.blog.api.blogapi.repository.PostRepository;
import com.blog.api.blogapi.services.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl  implements CommentService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post =this.postRepository.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post", "Post_Id", postId));
        
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        
       Comment comment= this.commentRepository.findById(commentId).orElseThrow(()-> new ResourseNotFoundException("Comment", "Comment_id", commentId));
       this.commentRepository.delete(comment);
        
    }
    
}
