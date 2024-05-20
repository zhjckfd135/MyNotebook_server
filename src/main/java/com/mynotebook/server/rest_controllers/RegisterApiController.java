package com.mynotebook.server.rest_controllers;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mynotebook.server.models.User;
import com.mynotebook.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterApiController {
    @Autowired
    UserService service;

    @PostMapping("/user/registerNewUser")
    public ResponseEntity registerNewName(@RequestParam("first_name") String first_name,
                                          @RequestParam("last_name") String last_name,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password){
        if(first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty())
            return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);

        User user = UserApiController.findUserByEmail(email, service);
        if(user != null)
            return new ResponseEntity<>("This email is busy", HttpStatus.BAD_REQUEST);

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        int res = service.registerNewUserServiceMethod(first_name, last_name, email, hashed_password);

        if(res != 1)
            return new ResponseEntity<>("Failed to Register User", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
