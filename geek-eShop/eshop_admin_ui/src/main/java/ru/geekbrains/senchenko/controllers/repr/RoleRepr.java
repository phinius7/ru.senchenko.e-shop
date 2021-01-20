package ru.geekbrains.senchenko.controllers.repr;

import ru.geekbrains.senchenko.entities.Role;

import java.util.Date;

public class RoleRepr {

    private Long id;
    private Date createDate;
    private Date modifyDate;
    private String title;

    public RoleRepr() {
    }

    public RoleRepr(Role role) {
        this.id = role.getId();
        this.createDate = role.getCreateDate();
        this.modifyDate = role.getModifyDate();
        this.title = role.getTitle();
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

    @Override
    public String toString() {
        return "RoleRepr{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
