package ru.geekbrains.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.senchenko.entities.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findBlogById(Long id);
}
