package org.example.userservice.services;

import org.example.userservice.exceptions.InvalidTokenException;
import org.example.userservice.exceptions.PasswordMismatchException;
import org.example.userservice.exceptions.UserNotFoundException;
import org.example.userservice.models.Token;
import org.example.userservice.models.User;
import org.example.userservice.repositories.TokenRepository;
import org.example.userservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, TokenRepository tokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
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


    public Token login(String email, String password) throws UserNotFoundException {
        Optional<User> userOptional =userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        User user = userOptional.get();
        if(!passwordEncoder.matches(password,user.getHashedPassword())) {
            throw new PasswordMismatchException("Password Entered is Incorrect");
        }
        Token token= Token.createToken(user);
        return tokenRepository.save(token);
    }


    public void logout(String tokenValue) {
        Optional<Token> tokenOptional= tokenRepository.findByValueAndDeleted(tokenValue, false);
        if (tokenOptional.isEmpty()) {
            throw new InvalidTokenException("The token is invalid, deleted, or has expired.");
        }
        Token token = tokenOptional.get();
        token.setDeleted(true);
        tokenRepository.save(token);
    }

    public User validateToken(String tokenValue) {
        Optional<Token> tokenOptional = tokenRepository.findByValueAndDeletedAndExpirationDateGreaterThan(
                tokenValue,
                false,
                new Date() // Current date
        );

        if (tokenOptional.isEmpty()) {
            throw new InvalidTokenException("The token is invalid, deleted, or has expired.");
        }

        return tokenOptional.get().getUser();
    }

}
