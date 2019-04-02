package com.paul_alarcon.igapppaul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText userInput;
    private EditText passInput;
    private Button btnInput;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.userInput);
        passInput = findViewById(R.id.passInput);
        btnInput = findViewById(R.id.btnInput);
        btnSignup = findViewById(R.id.btnSignup);


        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userInput.getText().toString();
                String password = passInput.getText().toString();
                login(username, password);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SigningUP();

            }
        });

    }

    private void SigningUP() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    private void login(String username, String password) {

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if( e != null){
                    Log.e(TAG, "Issue with Login!");
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Wrong password/username", Toast.LENGTH_SHORT).show();
                    return;
                }
                goHomeActivity();
                Log.e(TAG, "Signed in Successfully");
                Toast.makeText(MainActivity.this, "Welcome! ", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }



    private void goHomeActivity() {

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);

    }

}


