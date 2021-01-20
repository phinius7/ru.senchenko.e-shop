package ru.geekbrains.senchenko.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.senchenko.entities.Blog;
import ru.geekbrains.senchenko.entities.Category;
import ru.geekbrains.senchenko.entities.Picture;
import ru.geekbrains.senchenko.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT p.pictureData.data FROM Picture p WHERE p.id = :id")
    byte[] findPictureDataById(@Param("id") Long id);

    @Query("SELECT p.contentType FROM Picture p WHERE p.pictureData.id = :id")
    Optional<String> findPictureContentTypeByDataId(@Param("id") Long id);

    @Query("SELECT p.pictureData.fileName FROM Picture p WHERE p.id = :id")
    String findPictureFileNameByDataId(@Param("id") Long id);

    @Query("SELECT p FROM Picture p WHERE p.product = :product")
    List<Picture> findAllPicturesForProduct(@Param("product") Product product);

    @Query("SELECT p FROM Picture p WHERE p.category = :category")
    List<Picture> findPicturesForCategory(@Param("category") Category category);

    @Query("SELECT p FROM Picture p WHERE p.blog = :blog")
    List<Picture> findAllPicturesForBlog(@Param("blog") Blog blog);
}
