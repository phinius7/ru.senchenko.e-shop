package ru.geekbrains.senchenko.services;

import ru.geekbrains.senchenko.controllers.repr.RoleRepr;
import ru.geekbrains.senchenko.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.senchenko.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(RoleRepr roleRepr) {
        Role role = new Role();
        role.setId(roleRepr.getId());
        role.setCreateDate(roleRepr.getCreateDate());
        role.setModifyDate(roleRepr.getModifyDate());
        role.setTitle(roleRepr.getTitle());
        roleRepository.save(role);
    }

    @Override
    public List<RoleRepr> findAll() {
        return roleRepository.findAll().stream().map(RoleRepr::new).collect(Collectors.toList());
    }

    @Override
    public Optional<RoleRepr> findById(Long id) {
        return roleRepository.findById(id).map(RoleRepr::new);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
