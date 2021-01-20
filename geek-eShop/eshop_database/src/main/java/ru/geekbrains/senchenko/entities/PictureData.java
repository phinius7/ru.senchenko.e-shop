package ru.geekbrains.senchenko.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "picture_data")
public class PictureData extends CommonItem {

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "data", length = 33554430)
    private byte[] data;

    @Column(name = "file_name")
    private String fileName;

    public PictureData() {
    }

    public PictureData(byte[] data) {
        this.data = data;
    }

    public PictureData(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
