package com.blog.api.blogapi.exception;

import java.util.HashMap;
import java.util.List;

import com.blog.api.blogapi.payloads.ResponderApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptioHandler {
    
    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ResponderApi> resourceNotFoundExceptionHandler(ResourseNotFoundException ex){
       String message=ex.getMessage();
       ResponderApi responderApi=new ResponderApi(message,false);
       return new  ResponseEntity<>(responderApi,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        HashMap<String,String> map=new HashMap<>();
        List<ObjectError> al= ex.getBindingResult().getAllErrors();
        for(ObjectError error:al){
           String fieldName= ((FieldError)error).getField();
           String message=error.getDefaultMessage();
           map.put(fieldName,message);
        }
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
}
