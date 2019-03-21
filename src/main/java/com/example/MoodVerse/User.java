package com.example.MoodVerse;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.ArrayList;

public class User {


    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;

    private ArrayList<Color> moodHistory;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User() {
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }


    public User( String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.moodHistory = new ArrayList<>();

    }


}//end of class
