package ru.geekbrains.senchenko.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Blogs")
public class Blog extends CommonItem {

    @Column(name = "title", length = 128)
    private String title;

    @Column(name = "content", length = 4096)
    private String content;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    public Blog() {
    }

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
