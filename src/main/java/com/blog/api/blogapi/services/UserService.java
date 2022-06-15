package com.blog.api.blogapi.services;

import java.util.List;

import com.blog.api.blogapi.payloads.UserDto;




public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer id);
    UserDto getUserById(Integer id);
    List<UserDto>  getAllUsers();
    void deleteUser(Integer id);
}
