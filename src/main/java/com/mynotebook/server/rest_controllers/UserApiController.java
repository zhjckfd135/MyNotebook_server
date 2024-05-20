package com.mynotebook.server.rest_controllers;

import com.mynotebook.server.models.User;
import com.mynotebook.server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @GetMapping("/test")
    public String testEndpoint(){
        return "Test end point working";
    }

    public static User findUserByEmail(String email, UserService service){
        if(email.isEmpty())
            throw new NullPointerException();

        User res = service.foundUserByEmailServiceMethod(email);
        return res;
    }
}
