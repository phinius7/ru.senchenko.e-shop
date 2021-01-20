package ru.geekbrains.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.senchenko.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
