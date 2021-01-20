package ru.geekbrains.senchenko.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends CommonItem {

    @Column(name = "title", length = 32)
    private String title;

    @Column(name = "code", length = 32)
    private String code;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "category")
    List<Product> products = new ArrayList<>();

    public Category() {
    }

    public Category(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPicture(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "(" + code + ") " + title;
    }
}
