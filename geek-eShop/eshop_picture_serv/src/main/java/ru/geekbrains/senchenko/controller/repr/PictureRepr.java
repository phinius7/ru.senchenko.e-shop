package ru.geekbrains.senchenko.controller.repr;

import ru.geekbrains.senchenko.entities.Picture;

import java.util.Date;

public class PictureRepr {

    private Long id;
    private Date createDate;
    private Date modifyDate;
    private String name;
    private String contentType;

    public PictureRepr(Picture picture) {
        this.id = picture.getId();
        this.createDate = picture.getCreateDate();
        this.modifyDate = picture.getModifyDate();
        this.name = picture.getName();
        this.contentType = picture.getContentType();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
