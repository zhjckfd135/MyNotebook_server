package com.mynotebook.server.services;

import com.mynotebook.server.models.PrimaryProjection;
import com.mynotebook.server.models.Record;
import com.mynotebook.server.models.User;
import com.mynotebook.server.repository.UserRepository;
import com.mynotebook.server.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public static final int TOKEN_LENGTH = 16;
    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String first_name, String last_name, String email, String password){
        return userRepository.registerNewUser(first_name, last_name, email, password);
    }

    public User foundUserByEmailServiceMethod(String email){
        Iterable<User> users = userRepository.foundUserByEmail(email);
        return getUserFromIterable(users);
    }

    public User foundUserByIdServiceMethod(int id){
        Iterable<User> users = userRepository.foundUserById(id);
        return getUserFromIterable(users);
    }

    public User foundUserByTokenServiceMethod(String token){
        Iterable<User> users = userRepository.foundUserByToken(token);
        return getUserFromIterable(users);
    }

    public List<User> getUsers(){
        Iterable<User> users = userRepository.getUsers();
        List<User> usersList = new ArrayList<>();
        users.forEach(usersList::add);

        return usersList;
    }

    private static User getUserFromIterable(Iterable<User> users) {
        if(!users.iterator().hasNext())
            return null;
        return users.iterator().next();
    }

    public String generateAndSetToken(int user_id){
        String token = new RandomString(TOKEN_LENGTH).nextString();
        userRepository.setToken(user_id, token);
        return token;
    }
}
