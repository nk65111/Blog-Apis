package com.blog.api.blogapi.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private int postid;
    private String postTitle;
    private String postContent;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;


}
