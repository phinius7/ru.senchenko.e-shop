package ru.geekbrains.senchenko.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.senchenko.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    Optional<User> findUserByUserName(String name);

    @Query("SELECT u FROM User u WHERE u.userName = :username")
    User findByUsername(@Param("username") String username);
}
