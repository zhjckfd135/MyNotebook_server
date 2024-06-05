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
    @Query(value = "INSERT INTO users(first_name, last_name, email, password, token) VALUES(:first_name, :last_name, :email, :password, '')", nativeQuery = true)
    int registerNewUser(@Param("first_name") String first_name,
                        @Param("last_name") String last_name,
                        @Param("email") String email,
                        @Param("password") String password);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM users WHERE user_id = :id", nativeQuery = true)
    Iterable<User> foundUserById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    Iterable<User> getUsers();

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Iterable<User> foundUserByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM users WHERE token = :token", nativeQuery = true)
    Iterable<User> foundUserByToken(@Param("token") String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET token=:token WHERE user_id=:id", nativeQuery = true)
    int setToken(@Param("id") int id,
                            @Param("token") String token);
}
