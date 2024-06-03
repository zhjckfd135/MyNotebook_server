package com.mynotebook.server.rest_controllers;

import com.mynotebook.server.models.User;
import com.mynotebook.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {
    @Autowired
    UserService userService;


    @GetMapping("/test")
    public String testEndpoint(){
        return "Test end point working";
    }

    @GetMapping("/users")
    public ResponseEntity getUsers(){
        List<User> users = userService.getUsers();
        if(users == null)
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
