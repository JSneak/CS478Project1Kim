package com.example.cs478project1kim;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class Activity2 extends Activity {

    protected EditText editTextPersonName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Always do this
        super.onCreate(savedInstanceState);

//        Log.i("Activity2 ", "Activity2 is created");

        // Inflate the main layout (from the res folder)
        setContentView(R.layout.activity_2);

        // Bind interface elements to corresponding fields
        editTextPersonName = (EditText)findViewById(R.id.editTextPersonName);
        editTextPersonName.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    startActivity1(editTextPersonName.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    public void startActivity1(String name) {
//        Log.i("Activity2", "Entered startActivity1");
        Intent i = new Intent();

        /* Check if Name is in correct Format */
        if(onlyLetters(name) && checkIfFirstAndLast(name)) {
//            Log.i("Activity2", "onLetters True");
            i.putExtra("Name", name);
            setResult(RESULT_OK,i) ;
        } else {
            /* Else send the canceled */
//            Log.i("Activity2", "onLetters False");
            i.putExtra("Name", name);
            setResult(RESULT_CANCELED,i);
        }

        finish();
    }

    public boolean onlyLetters(@NonNull String name) {
        /*
            True -> Only Letters, False -> Not Only Letters
            Loop through string name
            Check if each character is a letter
         */
        Log.i("Activity2", "entered onlyLetters");
        for(int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
//            Log.i("onlyLetters", "Current Letter" + c);
            if(!Character.isLetter(c)) {
                if (c != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkIfFirstAndLast(@NonNull String name) {
        /* Regex to identify any spaces, whitespace, and tabs */
        String[] stringArray = (name.trim()).split("\\s+", 0);
        if(stringArray.length > 2 || stringArray.length < 2) {
            return false;
        }
        return true;
    }
}
