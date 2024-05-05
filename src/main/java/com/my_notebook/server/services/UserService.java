package com.my_notebook.server.services;

import com.my_notebook.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String first_name, String last_name, String email, String password){
        return userRepository.registerNewUser(first_name, last_name, email, password);
    }
}
