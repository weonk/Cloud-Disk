package com.chris.cloud.entity;

public class log {
    private Integer id;
    private Integer userId;
    private Integer fileIndex;
    private Integer action;
    private String data;

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

    public Integer getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(Integer fileIndex) {
        this.fileIndex = fileIndex;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "log{" +
                "id=" + id +
                ", userId=" + userId +
                ", fileIndex=" + fileIndex +
                ", action=" + action +
                ", data='" + data + '\'' +
                '}';
    }
}
