package com.mynotebook.server.rest_controllers;

import com.mynotebook.server.models.User;
import com.mynotebook.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginApiController {
    @Autowired
    UserService service;

    @GetMapping("/user/loginUser")
    public ResponseEntity loginUser(@RequestParam("email") String email,
                                          @RequestParam("password") String password){
        User user = UserApiController.findUserByEmail(email, service);
        if(user == null)
            return new ResponseEntity<>("Incorrect password or email", HttpStatus.BAD_REQUEST);


        if(!BCrypt.checkpw(password, user.getPassword()))
            return new ResponseEntity<>("Incorrect password or email", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
