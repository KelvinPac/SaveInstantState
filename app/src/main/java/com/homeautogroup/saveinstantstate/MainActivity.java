package com.homeautogroup.saveinstantstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Keys to identify the data we save
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_IS_SHOWN = "isShown";


    TextView textViewShowInputs;
    Button buttonShow;
    EditText inputName,inputEmail,inputPhone;

    String name,phone,email; // variables to hold our data
    boolean isShown = false; // variables to hold data if we already clicked the button to display inputs to the textview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////instantiate the fields
        textViewShowInputs = findViewById(R.id.textViewShowInput);
        buttonShow= findViewById(R.id.buttonShow);
        inputName = findViewById(R.id.editTextName);
        inputEmail = findViewById(R.id.editTextEmail);
        inputPhone = findViewById(R.id.editTextPhone);

        /*
        * The onCreate method has a Bundle argument from which we can retrieve any saved data
        * First check if its null because it will be for the first time
        * Second check if the Bundle contains the keys you used to save the data
        * Third.. Retrieve the data and show it to the textview*/
        if (savedInstanceState != null){

            if (savedInstanceState.containsKey(KEY_NAME) && savedInstanceState.containsKey(KEY_EMAIL)
                    && savedInstanceState.containsKey(KEY_PHONE) && savedInstanceState.containsKey(KEY_IS_SHOWN)){
                name = savedInstanceState.getString(KEY_NAME);
                email = savedInstanceState.getString(KEY_EMAIL);
                phone = savedInstanceState.getString(KEY_PHONE);
                isShown=savedInstanceState.getBoolean(KEY_IS_SHOWN);

                textViewShowInputs.append("Name: "+name+"\n");
                textViewShowInputs.append("Phone: "+phone+"\n");
                textViewShowInputs.append("Email: "+email+"\n");
            }
        }

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                * Check if we already shown the inputs  to the textview
                * if yes exit the function*/
                if (isShown){
                    return;
                }
                //get inputs from input fields
                name = inputName.getText().toString().trim();
                phone = inputPhone.getText().toString().trim();
                email = inputEmail.getText().toString().trim();

                //check if all fields are filled then proceed
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email) ){
                    /*
                    * the method append from the textview class enables us to add new text to a textview without clearing the existing text */
                    textViewShowInputs.append("Name: "+name+"\n");
                    textViewShowInputs.append("Phone: "+phone+"\n");
                    textViewShowInputs.append("Email: "+email+"\n");
                    isShown=true;
                }else {
                    Toast.makeText(MainActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /*This method is called before the activity is destroyed giving you a chance to save any data that may be lost
    * You save the data to the bundle using a key value pair*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email) ){
            outState.putString(KEY_NAME,name);
            outState.putString(KEY_EMAIL,email);
            outState.putString(KEY_PHONE,phone);
            outState.putBoolean(KEY_IS_SHOWN,isShown);
        }

    }
}
