package com.chris.cloud.entity;

public class FileList {
    private Integer id;
    private Integer userId;
    private String name;
    private String src;
    private String size;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FileList{" +
                "index=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", src='" + src + '\'' +
                ", size='" + size + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
