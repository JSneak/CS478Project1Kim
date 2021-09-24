package com.example.cs478project1kim;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class Activity1 extends ComponentActivity {

    protected Button button1 ; 				// the "Button 1" button in the GUI
    protected Button button2 ; 			// the "Button 2" button in the GUI
    protected Context context;
    protected String PersonName;

    /* Key into the "saved state" bundle */
    protected static final String name = "Name" ;


    ActivityResultLauncher<Intent> startActivity1Result = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                //this is where the result is returned and the logic of what to do with it goes
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent i = result.getData();
                        Log.i("Activity1: ", "Returned result is: " + result.getResultCode());
                        Log.i("Activity1: ", "Returned message from intent is: " + i.getStringExtra("Name"));
                        PersonName = i.getStringExtra("Name");
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Intent i = result.getData();
                        Log.i("Activity1: ", "Returned result is: " + result.getResultCode());
                        Log.i("Activity1: ", "Returned message from intent is: " + i.getStringExtra("Name"));
                        CharSequence text = "Invalid name: " + i.getStringExtra("Name");
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                }
            });

//    ActivityResultLauncher<Intent> startContactsActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//
//                //this is where the result is returned and the logic of what to do with it goes
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode() == Activity.RESULT_OK){
//                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
//                    }
//                }
//            });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Always do this
        super.onCreate(savedInstanceState);

        Log.i("Activity1 ", "Activity1 is created");

        // Inflate the main layout (from the res folder)
        setContentView(R.layout.activity_1);

        // Bind interface elements to corresponding fields
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        // Are we running from scratch?  Yes
        if (savedInstanceState == null) {
            /* Set Legal Name to null */
            PersonName = null;
        } else {
            /* Check for something here */
            Log.i("Activity1", "Saved state retrieved") ;
            PersonName = savedInstanceState.getString(name);
        }

        /* Setup listeners for buttons */
        button1.setOnClickListener(activity1Listener);
        button2.setOnClickListener(contactListener);
        context = getApplicationContext();
    }

    public View.OnClickListener activity1Listener = v -> switchToActivity1();

    private void switchToActivity1() {
        Intent i = new Intent(Activity1.this, Activity2.class);
        startActivity1Result.launch(i);
    }

    public View.OnClickListener contactListener = v -> switchToContactActivity();

    private void switchToContactActivity() {
        // Creates a new Intent to insert a contact
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, PersonName);

        // Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        startActivity(intent);
    }

//    // This will be called when the app loses focus; save
//    // current state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Always do this
        super.onSaveInstanceState(outState);

        Log.i("Activity1 ", "Activity1 lost focus");

        /* Save Legal Name from Activity 2 if needed */
        outState.putString(name, PersonName);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i("Activity1 ", "Activity1 is started");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.i("Activity1 ", "Activity1 is restarted");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i("Activity1 ", "Activity1 is resumed");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i("Activity1 ", "Activity1 is paused");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i("Activity1 ", "Activity1 is stopped");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i("Activity1 ", "Activity1 is Destroyed");
//    }

    /* ACTION_VIEW content://contacts/people/ */
}
