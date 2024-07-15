package com.example.recipesmainpage;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_username, et_password, et_cpassword,et_email, et_phone;
    Button btn_register, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        // Replace with actual ID in your layout

        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        et_cpassword = (EditText)findViewById(R.id.et_cpassword);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password = et_cpassword.getText().toString();

                if(username.equals("") || password.equals("") || confirm_password.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(confirm_password)){
                        Boolean checkusername = databaseHelper.CheckUsername(username);
                        if(checkusername == true){
                            Boolean insert = databaseHelper.Insert(username, password);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                et_username.setText("");
                                et_password.setText("");
                                et_cpassword.setText("");
                                // Start MainActivity after successful registration
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);

                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}