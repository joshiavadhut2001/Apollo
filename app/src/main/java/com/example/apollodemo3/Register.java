package com.example.apollodemo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    TextView already_signin;
    EditText username,email,password,confirmpassword;
    Button register;
    DbHandler my_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username=findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        confirmpassword=findViewById(R.id.confirmpassword);
        register = findViewById(R.id.registerbutton);
        already_signin=findViewById(R.id.gotologin);
        my_DB = new DbHandler(this);
        // to register data
      register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Register_user();
          }
      });
        //make listener for text already sign in
        already_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

    public void Register_user() {
                String Email=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String user=username.getText().toString().trim();
                String conpass = confirmpassword.getText().toString().trim();
                if(!isEmailValid(Email)){
                    Toast.makeText(getApplicationContext(), "Is Not Valid Email try Again", Toast.LENGTH_SHORT).show();
                }
                else if(!isPasswordvalid(pass)){
                    Toast.makeText(getApplicationContext(), "Too Small password length !!!",Toast.LENGTH_SHORT) .show();
                }
                else if(Email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Mail field Required !!", Toast.LENGTH_SHORT).show();

                }
                else if(pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Field Required !!", Toast.LENGTH_SHORT).show();
                }
                else if(!isconpass(pass,conpass))
                {
                    Toast.makeText(getApplicationContext(),"Confirm password again !!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(conpass) && isPasswordvalid(pass) && isEmailValid(Email))
                    {
                        boolean checkuser = my_DB.checkusername(user);
                        if(checkuser == false)
                        {
                            boolean isInserted= my_DB.insertData(Email,user,pass);
                            if(isInserted == true)
                            {
                                Toast.makeText(getApplicationContext(),"You are sucessfully Registered",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Account is already exists",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Username already exist please login!!",Toast.LENGTH_SHORT).show();
                        }
                    }


                }
    }

    public boolean isEmailValid(String email){
        return email.contains("@");
    }
    public boolean isPasswordvalid(String password){
        return password.length()>6;
    }
    public boolean isconpass(String pass,String conpass)
    {
        if(pass.equals(conpass))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}