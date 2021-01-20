package ru.geekbrains.senchenko.services;

import ru.geekbrains.senchenko.controllers.repr.UserRepr;
import ru.geekbrains.senchenko.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(UserRepr userRepr);

    List<UserRepr> findAll();

    Optional<UserRepr> findById(Long id);

    void delete(Long id);
}

