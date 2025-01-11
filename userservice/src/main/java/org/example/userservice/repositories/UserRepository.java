package org.example.userservice.repositories;

import jakarta.persistence.OneToMany;
import org.example.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);// this is update + insert method.

    Optional<User> findByEmail(String email);
}
