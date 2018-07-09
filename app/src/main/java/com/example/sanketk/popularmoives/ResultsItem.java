package com.example.sanketk.popularmoives;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ResultsItem {
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("id")
    private int id;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private boolean video;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("vote_count")
    private int voteCount;

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return this.originalTitle;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isVideo() {
        return this.video;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Integer> getGenreIds() {
        return this.genreIds;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return this.backdropPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return this.voteAverage;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getPopularity() {
        return this.popularity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isAdult() {
        return this.adult;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return this.voteCount;
    }

    public String toString() {
        return "ResultsItem{overview = '" + this.overview + '\'' + ",original_language = '" + this.originalLanguage + '\'' + ",original_title = '" + this.originalTitle + '\'' + ",video = '" + this.video + '\'' + ",title = '" + this.title + '\'' + ",genre_ids = '" + this.genreIds + '\'' + ",poster_path = '" + this.posterPath + '\'' + ",backdrop_path = '" + this.backdropPath + '\'' + ",release_date = '" + this.releaseDate + '\'' + ",vote_average = '" + this.voteAverage + '\'' + ",popularity = '" + this.popularity + '\'' + ",id = '" + this.id + '\'' + ",adult = '" + this.adult + '\'' + ",vote_count = '" + this.voteCount + '\'' + "}";
    }
}
