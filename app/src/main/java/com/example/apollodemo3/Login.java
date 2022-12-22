package com.example.apollodemo3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class Login extends AppCompatActivity {
    EditText loginemail, loginpassword;
    Button buttonlogin;
    TextView signuptext;
    DbHandler my_DB;


    private static String value;
    public static String getValue() {
        return value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginemail = findViewById(R.id.loginemail);
        loginpassword = findViewById(R.id.loginpassword);
        signuptext = findViewById(R.id.Signuptext);
        buttonlogin = findViewById(R.id.buttonlogin);
        my_DB = new DbHandler(this);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attempt_login();
            }
        });

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempt_register();
            }
        });

    }

    public boolean isPasswordValid(String pass) {
        return (pass.length() > 6);
    }


    public boolean isEmailValid(String emails) {
        return emails.contains("@");

    }
    public void attempt_login() {
        String Email = loginemail.getText().toString().trim();
        String pass = loginpassword.getText().toString().trim();

        if(Email.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "You are missing Email or Password", Toast.LENGTH_SHORT).show();
        }
        if (!isEmailValid(Email)) {
            Toast.makeText(getApplicationContext(), "Email Invalid", Toast.LENGTH_SHORT).show();
        }
        if (!isPasswordValid(pass)) {
            Toast.makeText(getApplicationContext(), "Password Invalid", Toast.LENGTH_SHORT).show();
        }
        boolean res = my_DB.checkusernamepassword(Email, pass);
        if (res == true) {
            value=Email;
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid Credentials or Please Signup", Toast.LENGTH_SHORT).show();
        }

    }

    public void attempt_register()
    {
        final Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }

    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn){

            if(loginpassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.hidepass);

                //Show Password
                loginpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.showpass);
                //Hide Password
                loginpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

}