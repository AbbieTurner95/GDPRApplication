package com.example.abbieturner.gdprapplication.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.Settings;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static boolean isValid(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static final boolean checkEmail(EditText email) {
        if (!isEmailValid(email.getText().toString())) {
            email.setError("Please check e-mail address is correct.");
            return false;
        }
        return true;
    }

    public static final boolean checkError(EditText editText) {
        if (!isValid(editText.getText().toString())) {
            editText.setError("Please fill out this empty field.");
            return false;
        }
        return true;
    }

    public static boolean checkPhoneSize(EditText mPhone) {
        if (mPhone.getText().toString().length() < 6) {
            mPhone.setError("Phone size must be 11 characters.");
            return false;
        }
        return true;
    }

    public static void goToImageSettings(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage("we want permission to can upload image")
                .setPositiveButton("settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", context.getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }
}