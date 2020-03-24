package com.example.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseModel<T> {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<T> result;

    public BaseModel(){

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
