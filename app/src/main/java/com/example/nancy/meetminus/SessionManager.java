package com.example.nancy.meetminus;

/**
 * Created by nancy on 2018-01-14.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.Serializable;

public class SessionManager implements Serializable{

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    private static final String TAG = "SessionManager";

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    public static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Constructor
    public SessionManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email, String pasword){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        Log.d(TAG, "createLoginSession: storing userSettings in json format...");
        pref.edit().putString("EMAIL", email);
        pref.edit().putString("PASSWORD", pasword);

        // commit changes
        editor.commit();
    }

//    public UserSettings getUserDetails(){
//        UserSettings userSettings = null;
//
//        try {
//            String savedValue = pref.getString("userSettings", null);
//            if (savedValue.equals("")) {
//                userSettings = null;
//            } else {
//                userSettings = gson.fromJson(savedValue, UserSettings.class);
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        // return user
//        return userSettings;
//    }


    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

//        // After logout redirect user to Loing Activity
//        Intent i = new Intent(context, LoginActivity.class);
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring Login Activity
//        context.startActivity(i);
    }

}