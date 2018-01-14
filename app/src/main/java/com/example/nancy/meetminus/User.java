package com.example.nancy.meetminus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import io.left.rightmesh.id.MeshID;

/**
 * Created by nancy on 2018-01-13.
 */

public class User {
  //  public static HashSet<User>
    public String userID;
    private String name;
    private String username;
    private String email;
    private String number;
    private String password;

    private Map<User, String> friends = new HashMap<>();

    private MeshID meshID;
    private double Longitude;
    private double Latitude;

    public User(String email, String password) {
        this.password = password;


        this.email = email;
    }

    public Map<User, String> getFriends(){
        return friends;
    }

    public void setMeshID(MeshID id){
        this.meshID = id;
    }
    public String getUsername(){
        return username;
    }

    public MeshID getMeshID(){ return meshID; }

    public void setLongitude(double longitude){
        this.Longitude = longitude;

    }

    public void setLatitude(double latitude){
        this.Latitude = latitude;
    }

    public double getLongitude(){
        return this.Longitude;
    }

    public double getLatitude(){
        return this.Latitude;
    }

    public void addFriend(User user, String Category){

        //get the user from firebase
        this.friends.put(user, Category);
    }




}