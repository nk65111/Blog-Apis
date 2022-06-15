package com.blog.api.blogapi.controllers;

import java.util.List;

import javax.validation.Valid;

import com.blog.api.blogapi.payloads.ResponderApi;
import com.blog.api.blogapi.payloads.UserDto;
import com.blog.api.blogapi.services.UserService;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
     
    @Autowired
    private UserService userService;
 
    
    //create new user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
       UserDto saveuserDto= this.userService.createUser(userDto);
       return new ResponseEntity<>(saveuserDto,HttpStatus.CREATED);
    }
    
    //get detail of perticular user 
    @GetMapping("/{userid}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userid") Integer uid){
      UserDto userDto = this.userService.getUserById(uid); 
      return ResponseEntity.ok(userDto);
    }
 
    //get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
       List<UserDto> users= this.userService.getAllUsers();
       return ResponseEntity.ok(users);
    }
    
    //update user details
    @PutMapping("/{userid}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userid") Integer uid){
       UserDto updatedUser= this.userService.updateUser(userDto, uid);
       return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }


   @DeleteMapping("/{userid}")
   public ResponseEntity<ResponderApi> deleteUser(@PathVariable("userid") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<>(new ResponderApi("user successfully delete",true),HttpStatus.ACCEPTED);
   }


}
