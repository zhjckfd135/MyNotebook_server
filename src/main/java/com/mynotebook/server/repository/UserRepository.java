package com.mynotebook.server.repository;

import com.mynotebook.server.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_manager.users(first_name, last_name, email, password) VALUES(:first_name, :last_name, :email, :password)", nativeQuery = true)
    int registerNewUser(@Param("first_name") String first_name,
                        @Param("last_name") String last_name,
                        @Param("email") String email,
                        @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM user_manager.users WHERE email = :email", nativeQuery = true)
    Iterable<User> foundUserByEmail(@Param("email") String email);
}
