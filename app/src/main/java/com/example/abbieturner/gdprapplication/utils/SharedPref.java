package com.example.abbieturner.gdprapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.abbieturner.gdprapplication.Models.User;
import com.google.gson.Gson;

public class SharedPref {

    private Context context;
    private static final String SHARED_PREF_KEY = "shared_key";
    private static final String USER_LOGGED = "user_logged";
    public static final String USER_ID = "user_id";

    public SharedPref(Context context) {
        this.context = context;
    }

    public void setUserData(User userData) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_LOGGED, new Gson().toJson(userData));
        editor.apply();
    }

    public User getUserData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, 0);
        return new Gson().fromJson(sharedPreferences.getString(USER_LOGGED, null), User.class);
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, 0);
        return sharedPreferences.getString(USER_ID, "");
    }

    public void setUserId(String userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, userId);
        editor.apply();
    }
}
