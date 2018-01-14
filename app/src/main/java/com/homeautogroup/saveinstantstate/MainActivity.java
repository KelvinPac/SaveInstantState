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

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";


    TextView textViewShowInputs;
    Button buttonShow;
    EditText inputName,inputEmail,inputPhone;

    String name,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////
        textViewShowInputs = findViewById(R.id.textViewShowInput);
        buttonShow= findViewById(R.id.buttonShow);
        inputName = findViewById(R.id.editTextName);
        inputEmail = findViewById(R.id.editTextEmail);
        inputPhone = findViewById(R.id.editTextPhone);

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = inputName.getText().toString().trim();
                phone = inputPhone.getText().toString().trim();
                email = inputEmail.getText().toString().trim();

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email) ){
                    textViewShowInputs.append("Name: "+name+"\n");
                    textViewShowInputs.append("Phone: "+phone+"\n");
                    textViewShowInputs.append("Email: "+email+"\n");
                }else {
                    Toast.makeText(MainActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null){

            if (savedInstanceState.containsKey(KEY_NAME) && savedInstanceState.containsKey(KEY_EMAIL) && savedInstanceState.containsKey(KEY_PHONE)){
                name = savedInstanceState.getString(KEY_NAME);
                email = savedInstanceState.getString(KEY_EMAIL);
                phone = savedInstanceState.getString(KEY_PHONE);

                textViewShowInputs.append("Name: "+name+"\n");
                textViewShowInputs.append("Phone: "+phone+"\n");
                textViewShowInputs.append("Email: "+email+"\n");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email) ){
            outState.putString(KEY_NAME,name);
            outState.putString(KEY_EMAIL,email);
            outState.putString(KEY_PHONE,phone);
        }

    }
}
