package com.productservice.products.commons;

import com.productservice.products.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommans {
    private RestTemplate restTemplate;

    public AuthCommans(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String tokenValue){
        try {
            ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(
                    "http://localhost:8081/users/validate/" + tokenValue,
                    UserDto.class
            );
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return null; // Token is invalid
            }
            throw e; // Handle unexpected exceptions
        }
    }

}
