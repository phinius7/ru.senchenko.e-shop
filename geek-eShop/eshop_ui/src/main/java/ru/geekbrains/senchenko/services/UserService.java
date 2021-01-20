package ru.geekbrains.senchenko.services;

import ru.geekbrains.senchenko.controllers.repr.UserRepr;
import ru.geekbrains.senchenko.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepr> findAll();

    Optional<UserRepr> findById(Long id);

    void delete(Long id);

    UserRepr getCurrentUser();

    User createUser(UserRepr userRepr);

    void authenticateUser(User user);
}

