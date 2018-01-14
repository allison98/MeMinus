package com.example.nancy.meetminus;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import io.left.rightmesh.id.MeshID;

/**
 * Created by nancy on 2018-01-13.
 */

public class User implements Parcelable{
  //  public static HashSet<User>
    public String userID;
    private static int v = 0;
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

        this.userID =""+ v++;
        this.email = email;
    }

    protected User(Parcel in) {
        userID = in.readString();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        number = in.readString();
        password = in.readString();
        Longitude = in.readDouble();
        Latitude = in.readDouble();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userID);
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(number);
        parcel.writeString(password);
        parcel.writeDouble(Longitude);
        parcel.writeDouble(Latitude);
    }
}