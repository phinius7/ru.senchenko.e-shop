package ru.geekbrains.senchenko.entities;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture extends CommonItem{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "pcture_data_id")
    private PictureData pictureData;

    @ManyToOne
    private Product product;

    @OneToOne
    private Category category;

    @ManyToOne
    private Blog blog;

    public Picture() {
    }

    public Picture(String name, String contentType, PictureData pictureData, Product product) {
        this.name = name;
        this.contentType = contentType;
        this.pictureData = pictureData;
        this.product = product;
    }

    public Picture(String name, String contentType, PictureData pictureData, Category category) {
        this.name = name;
        this.contentType = contentType;
        this.pictureData = pictureData;
        this.category = category;
    }

    public Picture(String name, String contentType, PictureData pictureData, Blog blog) {
        this.name = name;
        this.contentType = contentType;
        this.pictureData = pictureData;
        this.blog = blog;
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

    public PictureData getPictureData() {
        return pictureData;
    }

    public void setPictureData(PictureData pictureData) {
        this.pictureData = pictureData;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
