package com.amolsoftwares.www.mymultithreadingapp.controller;

import com.amolsoftwares.www.mymultithreadingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity saveUsers(@RequestParam(value = "files") MultipartFile[] files) throws Exception {

        for (MultipartFile file: files){
            userService.saveUsers(file);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/users")
    public CompletableFuture<ResponseEntity> findAllUsers(){
        return userService.findAllUsers().thenApply(ResponseEntity::ok);
    }
}
