package com.blog.api.blogapi.services.Impl;

import java.util.ArrayList;
import java.util.List;


import com.blog.api.blogapi.entities.User;
import com.blog.api.blogapi.exception.ResourseNotFoundException;
import com.blog.api.blogapi.payloads.UserDto;
import com.blog.api.blogapi.repository.UserRepository;
import com.blog.api.blogapi.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=getUserDtoToUser(userDto);
        User u=userRepository.save(user);
        UserDto udto=getUserToUserDto(u);
        return udto;
    }

    @Override
    public void deleteUser(Integer id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("user","id",id));
        this.userRepository.delete(user);
       
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users= this.userRepository.findAll();
        List<UserDto> userdtos=new ArrayList<>();
        for(User user:users){
            userdtos.add(getUserToUserDto(user));
        }
        return userdtos;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("user","id",id));
        return getUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
       User user=this.userRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("user", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser=this.userRepository.save(user);
        return getUserToUserDto(savedUser);
        
    }

    private User getUserDtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto, User.class);
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());
        return user;
    }
    private UserDto getUserToUserDto(User user){
        UserDto userDto=this.modelMapper.map(user, UserDto.class);
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getAbout());
        return userDto;
    }
    
}
