package com.example.MoodVerse;

import java.sql.Date;

public class Movie {

    // private int movieId;
    private String title;
    private Date year;
    private String Omdb_Id;
    private String Poster_path;
    private String Description;
    private Float Popularity;
    private String mood;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() { return year; }

    public void setYear(Date year) { this.year = year; }

    public String getOmdb_Id() {
        return Omdb_Id;
    }

    public void setOmdb_Id(String omdb_Id) {
        Omdb_Id = omdb_Id;
    }

    public String getPoster_path() {
        return Poster_path;
    }

    public void setPoster_path(String poster_path) {
        Poster_path = poster_path;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Float getPopularity() { return Popularity;}

    public void setPopularity(Float popularity) {Popularity = popularity;}

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    // NEED TO ADD MORE INFO SO WE CAN GET A GOOD MAPPING WITH OUR DATABASE

    public Movie() {
    }

    // added constuctor to work with movie helper in DBrepo


    public Movie(String title, Date year, String omdb_Id, String poster_path, String description, Float popularity, String mood) {
        this.title = title;
        this.year = year;
        this.Omdb_Id = omdb_Id;
        this.Poster_path = poster_path;
        this.Description = description;
        this.Popularity = popularity;
        this.mood = mood;
    }


}//end of class


