package ru.geekbrains.senchenko.controllers.repr;

import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.senchenko.entities.Category;

import java.util.Date;

public class CategoryRepr {

    private Long id;
    private Date createDate;
    private Date modifyDate;
    private String title;
    private String code;
    private MultipartFile[] pictures;

    public CategoryRepr() {
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.createDate = category.getCreateDate();
        this.modifyDate = category.getModifyDate();
        this.title = category.getTitle();
        this.code = category.getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MultipartFile[] getPictures() {
        return pictures;
    }

    public void setPictures(MultipartFile[] pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return title;
    }
}
