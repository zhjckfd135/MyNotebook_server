package com.my_notebook.server.rest_controllers;

import com.my_notebook.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterApiController {
    @Autowired
    UserService service;

    @PostMapping("/user/register")
    public ResponseEntity registerNewName(@RequestParam("first_name") String first_name,
                                          @RequestParam("last_name") String last_name,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password){
        if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty())
            return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        int res = service.registerNewUserServiceMethod(first_name, last_name, email, hashed_password);

        if(res != 1)
            return new ResponseEntity<>("Failed to Register User", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    }
}
