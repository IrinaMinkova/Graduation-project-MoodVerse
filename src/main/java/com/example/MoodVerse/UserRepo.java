package com.example.MoodVerse;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service //this will be @Repository
public class UserRepo {

    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users.add(users);
    }

    public UserRepo() {
   /*     this.users = new ArrayList<>();
        this.users.add(new User("Guest","guest"));
        this.users.add(new User("Shubham","shubham"));
        this.users.add(new User("Junaid","Junaid"));
        this.users.add(new User("Irina","Irina"));
        this.users.add(new User("Joy","Joy"));*/
    }

    public User getUser(int index){
        User user = null;
        if(index >= 0 && index < users.size()){
            user = users.get(index);
        }
        else{
            user = null;
        }
        return user;
    }

    public User verification(User inData){
        // this function need to get data from DB and verify.
        // we will use JDBC to send a query to the user table
        // and check if that user exits in the DB and if the infomration
        // match then we set that verification is passed. SO return
        // the current user as a logged in one. IF we don't find
        // a user in the database, then we return NULL:


        User outData = null;//users.get(0);//create it in the beginning
/*
         for(User m:this.users){
            System.out.println("in the loop "+m.getUserName());
            if( (inData.getUserName().equals(m.getUserName())) && (inData.getPassword().equals(m.getPassword())) ){
                outData = m;
                System.out.println("Verification  "+outData.getUserName());
                break;
            }
        }
*/
        return outData;
    }

  /*  public User verification(String username, String password){
        // this function need to get data from DB and verify.
        // we will use JDBC to send a query to the user table
        // and check if that user exits in the DB and if the infomration
        // match then we set that verification is passed. SO return
        // the current user as a logged in one.

        User outData = null;
        for(User m:this.users){
            if( (username == m.getUserName()) && (password == m.getPassword()) ){
                outData = m;
            }
            else {
                outData = null;//this.users.get(0); //return guest user
            }
        }

        return outData;
    }*/


    public int size(){
        return users.size();
    }
}
