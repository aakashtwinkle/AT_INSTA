package com.example.at_insta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class loginactivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText emaillogin,usernamelogin,passwordlogin;
    private Button buttonlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        emaillogin=findViewById(R.id.emaillogin);
        usernamelogin=findViewById(R.id.usernamelogin);
        passwordlogin=findViewById(R.id.passwordlogin);
        buttonlogin=findViewById(R.id.buttonlogin);
        setTitle("LOG IN");
        buttonlogin.setOnClickListener(this);
        passwordlogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                    onClick(buttonlogin);
                return false;
            }
        });

        }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonlogin:
                if (emaillogin.getText().toString().equals("") || usernamelogin.getText().toString().equals("") || passwordlogin.getText().toString().equals("")) {
                    FancyToast.makeText(loginactivity.this, "All fields are compulsory", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }else {
                    ParseUser.logInInBackground(emaillogin.getText().toString(), passwordlogin.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(loginactivity.this, user.getUsername() + " Log In Succesull  !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitiontosocialmediaAcivity();
                            } else {
                                FancyToast.makeText(loginactivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }
            break;
        }
    }
    public void clickloginlaayout(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitiontosocialmediaAcivity(){
        Intent intent=new Intent(loginactivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
