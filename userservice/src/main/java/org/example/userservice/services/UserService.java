package org.example.userservice.services;

import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User signUp(String email,String name,String password) {
        User user= new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(passwordEncoder.encode(password));
        user.setEmailVerified(true);
        //PERSIST THE USER TO DB.
        return userRepository.save(user);
    }


    public Token login(String email, String password) {
        return new Token();
    }

    public void logout(String token) {

    }

    public User validateToken(String token) {
        return null;
    }

}
