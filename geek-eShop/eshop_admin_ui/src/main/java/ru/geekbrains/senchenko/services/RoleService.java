package ru.geekbrains.senchenko.services;

import ru.geekbrains.senchenko.controllers.repr.RoleRepr;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void save(RoleRepr roleRepr);

    List<RoleRepr> findAll();

    Optional<RoleRepr> findById(Long id);

    void delete(Long id);
}
