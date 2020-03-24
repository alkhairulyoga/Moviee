package com.example.movie.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseModel<T> {
@SerializedName("page")
private int page;

@SerializedName("results")
private List<MovieData> results;

public BaseModel(){
        }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieData> getResults() {
        return results;
    }

    public void setResults(List<MovieData> results) {
        this.results = results;
    }
}
