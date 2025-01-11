package org.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String value;
    @ManyToOne
    private User user;
    private Date expirationDate;

    public static Token createToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(128)); // Example token value

        // Set the expiration date
        LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(1); // Token expires in 1 day
        Instant expirationInstant = expirationDateTime.atZone(ZoneId.systemDefault()).toInstant();
        token.setExpirationDate(Date.from(expirationInstant));

        return token;
    }

}
