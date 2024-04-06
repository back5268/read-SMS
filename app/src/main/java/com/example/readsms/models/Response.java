package com.example.readsms.models;

import com.google.gson.annotations.SerializedName;

public class Response {
    private boolean status;
    private String mess;
    @SerializedName("body") // Chú thích này là cần thiết nếu khóa JSON khác với tên trường
    private String data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
