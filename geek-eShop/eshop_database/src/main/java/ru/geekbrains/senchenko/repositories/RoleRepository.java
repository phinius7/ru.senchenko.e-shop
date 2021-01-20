package ru.geekbrains.senchenko.repositories;

import ru.geekbrains.senchenko.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByTitle(String title);

    Role findRoleById(Long id);
}
