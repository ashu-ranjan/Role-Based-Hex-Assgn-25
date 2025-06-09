package com.hexJune.CodingTest;

import com.hexJune.CodingTest.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    /*
     * Whenever a RuntimeException is thrown in controller, this method gets called
     * */
    @ExceptionHandler(exception = RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException e){
        Map<String,String> map = new HashMap<>();
        map.put("msg" , e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(map);
    }

    /*
     * Whenever a ResourceNotFoundException is thrown in controller, this method gets called
     * */
    @ExceptionHandler(exception = ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e){
        Map<String,String> map = new HashMap<>();
        map.put("msg" , e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(map);
    }

    /* Add commentMore actions
     * Whenever a token is invalid ,
     * this method gets called
     * */
    @ExceptionHandler(exception = SecurityException.class)
    public ResponseEntity<?> handleSignatureException(Exception e){
        Map<String, String> map = new HashMap<>();
        map.put("msg", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(map);
    }

    /*
     * Whenever an unforeseen Exception is thrown in controller, this method gets called
     * */
    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        Map<String,String> map = new HashMap<>();
        map.put("msg" , e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(map);
    }
}
