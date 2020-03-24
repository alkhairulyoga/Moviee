package com.example.movie.Model;

import com.google.gson.annotations.SerializedName;

public class Movie extends BaseModel<MovieData>{
@SerializedName("total_pages")
private int totalPages;

@SerializedName("total_results")
private int totalResults;

public Movie(){
}

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
