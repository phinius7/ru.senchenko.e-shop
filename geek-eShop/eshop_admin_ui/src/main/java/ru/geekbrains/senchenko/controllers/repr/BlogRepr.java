package ru.geekbrains.senchenko.controllers.repr;

import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.senchenko.entities.Blog;

import java.util.Date;

public class BlogRepr {

    private Long id;
    private Date createDate;
    private Date modifyDate;
    private String title;
    private String content;
    private MultipartFile[] newPictures;

    public BlogRepr() {
    }

    public BlogRepr(Blog blog) {
        this.id = blog.getId();
        this.createDate = blog.getCreateDate();
        this.modifyDate = blog.getModifyDate();
        this.title = blog.getTitle();
        this.content = blog.getContent();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }
}
