package com.example.prrcmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;


public class Login extends AppCompatActivity {

    Button btn_login;
    EditText et_username;
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap postData = new HashMap();
                postData.put("btn_Login", "Login");
                postData.put("mobile", "android");
                postData.put("txtUsername", et_username.getText().toString());
                postData.put("txtPassword", et_password.getText().toString() );

                PostResponseAsyncTask loginTask =
                        new PostResponseAsyncTask(Login.this, postData, new AsyncResponse() {
                            @Override
                            public void processFinish(String output) {
                                if(output.equals("success")){
                                    Intent intent = new Intent(Login.this, Home.class);
                                    startActivity(intent);
                                    /*Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();*/
                                }
                            }
                        });
                //Use 10.0.3.2 for GenyMotion
                //Use 10.0.2.2 for Android native Emulator
                //If you're going to use a real Android device, use your laptop's IP Address (type 'ipconfig' in CMD for details)
                loginTask.execute("http://10.0.2.2/android/login.php");
            }
        });


    }

}
