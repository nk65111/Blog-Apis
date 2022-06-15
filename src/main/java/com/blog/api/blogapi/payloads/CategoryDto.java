package com.blog.api.blogapi.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private int catId;

    @NotEmpty
    @Size(min=4,message = "title should have atleast 4 character")
    private String categoryTitle;

    @NotEmpty
    @Size(min=10,message = "description should have atleast 10 charcter")
    private String categoryDescription;
}
