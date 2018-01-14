package com.example.nancy.meetminus;

import io.left.rightmesh.id.MeshID;

/**
 * Created by nancy on 2018-01-13.
 */

public class User {
    private int count = 0;
    private String username;
    private String email;
    private String nubmer;
    final MeshID meshID;

    public User(String username, String email, String number, MeshID meshID) {

        this.username = username;
        this.meshID = meshID;
        this.email = email;
        this.nubmer = number;
    }


}
