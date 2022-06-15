package com.blog.api.blogapi.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 3,message = "name should contain atleast 3 character")
    private String name;

    @Email(message = "email is not valid")
    private String email;

    @NotEmpty
    @Size(min=4,max = 14,message = "password contains atleast 4 character and atmost 14 character")
    private String password;

    @NotNull
    private String about;
}
