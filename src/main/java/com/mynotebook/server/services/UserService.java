package com.mynotebook.server.services;

import com.mynotebook.server.models.User;
import com.mynotebook.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String first_name, String last_name, String email, String password){
        return userRepository.registerNewUser(first_name, last_name, email, password);
    }

    public User foundUserByEmailServiceMethod(String email){
        Iterable<User> users = userRepository.foundUserByEmail(email);
        if(!users.iterator().hasNext())
            return null;
        User user = users.iterator().next();
        return user;
    }

    public User loginUserServiceMethod(String email, String password){
        User user = userRepository.foundUserByEmail(email).iterator().next();
        return user;
    }
}
