package org.example.userservice.repositories;

import org.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Override
    Token save(Token token);

    Optional<Token> findByValueAndDeleted(String tokenValue, boolean b);

    Token findByValue(String tokenValue);

    Optional<Token> findByValueAndDeletedAndExpirationDateGreaterThan(String tokenValue, boolean b, Date date);
}
