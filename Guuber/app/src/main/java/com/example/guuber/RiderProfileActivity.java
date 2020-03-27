package com.example.guuber;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guuber.model.User;


/**
 * Code to display riders information on their profile
 */

public class RiderProfileActivity extends AppCompatActivity {

    String username;
    String email;
    String phoneNumber;
    TextView emailField;
    TextView usernameField;
    TextView phoneNumberField;
    Button deleteButton;
    ImageView likeButton;
    ImageView dislikeButton;
    ImageView profileImg;
    User userInfo;
    Boolean editable;

    private static final String TAG = "RiderProfileActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rider_profile_disp);
        userInfo = ((UserData)(getApplicationContext())).getUser();

        String caller = getIntent().getStringExtra("caller");
        editable = caller.equals("internal");

        /**display the back button**/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phoneNumberField = findViewById(R.id.phoneTextRdIn);
        usernameField = findViewById(R.id.usernameTextRdIn);
        emailField = findViewById(R.id.emailTextRdIn);
        likeButton = findViewById(R.id.likeButtonRdIn);
        dislikeButton = findViewById(R.id.dislikeButtonRdIn);
        profileImg = findViewById(R.id.imageViewRdIn);

        phoneNumber = userInfo.getPhoneNumber();
        username = userInfo.getUsername();
        email = userInfo.getEmail();

        /**
         * allows for editing userdata
         */
        if (editable) {
            phoneNumberField.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    EditUserdataFragment fragment = new EditUserdataFragment();
                    Bundle phoneBundle = new Bundle();
                    phoneBundle.putString("field", "phone number");
                    phoneBundle.putString("old", phoneNumber);
                    phoneBundle.putString("activity", "RiderProfileActivity");
                    fragment.setArguments(phoneBundle);
                    fragment.show(getSupportFragmentManager(), "Edit Phone Number");
                    return true;
                }
            });

            usernameField.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    EditUserdataFragment fragment = new EditUserdataFragment();
                    Bundle phoneBundle = new Bundle();
                    phoneBundle.putString("field", "username");
                    phoneBundle.putString("old", username);
                    phoneBundle.putString("activity", "RiderProfileActivity");
                    fragment.setArguments(phoneBundle);
                    fragment.show(getSupportFragmentManager(), "Edit Phone Number");
                    return true;
                }
            });

            emailField.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    EditUserdataFragment fragment = new EditUserdataFragment();
                    Bundle phoneBundle = new Bundle();
                    phoneBundle.putString("field", "email");
                    phoneBundle.putString("old", email);
                    phoneBundle.putString("activity", "RiderProfileActivity");
                    fragment.setArguments(phoneBundle);
                    fragment.show(getSupportFragmentManager(), "Edit Phone Number");
                    return true;
                }
            });
        }

        phoneNumberField.setText(phoneNumber);
        // for testing please disregard
        Log.d(TAG, "documentSnapshot.getString(\"phoneNumber\")" +" "+userInfo.getPhoneNumber());
        usernameField.setText(username);
        emailField.setText(email);
        likeButton.setImageResource(R.drawable.smile);
        dislikeButton.setImageResource(R.drawable.frowny);
        profileImg.setImageResource(R.drawable.profilepic);

        deleteButton = findViewById(R.id.deleteAccButtonRdIn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteAccountFragment().show(getSupportFragmentManager(), "Delete Account");
            }
        });
    }
    /**implement logic here for what you want to
     * happen upon back button press**/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateData(String field, String value){

        if (field.equals("email")){
            userInfo.setEmail(value);
        }
        else if (field.equals("phone number")){
            userInfo.setPhoneNumber(value);
        }
        else if (field.equals("username")){
            userInfo.setUsername(value);
        }
    }
}
