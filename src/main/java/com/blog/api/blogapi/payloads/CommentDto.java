package com.blog.api.blogapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private String commentId;

    private String commentContent;
    private PostDto postDto;
}
