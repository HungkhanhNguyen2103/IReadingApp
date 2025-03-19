package com.example.basemodel;

import com.example.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReponderModel<T> {
    private int statusCode;
    private boolean isSussess;
    private String message;
    private T data;
    private List<T> dataList;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSussess() {
        return isSussess;
    }

    public void setSussess(boolean sussess) {
        isSussess = sussess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
