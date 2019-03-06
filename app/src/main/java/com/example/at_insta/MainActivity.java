package com.example.at_insta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private EditText emailsignup,usernamesignup,passwordsignup;
private Button buttonsignup,buttonloginmodule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailsignup=findViewById(R.id.emailsignup);
        usernamesignup=findViewById(R.id.usernamesignup);
        passwordsignup=findViewById(R.id.passwordsignup);
        buttonloginmodule=findViewById(R.id.buttonloginmodule);
        buttonsignup=findViewById(R.id.buttonsignup);
        setTitle("Sign Up");
        buttonsignup.setOnClickListener(this);
        buttonloginmodule.setOnClickListener(this);
        if (ParseUser.getCurrentUser()!=null) {
            // ParseUser.getCurrentUser().logOut();
            transitiontosocialmediaAcivity();
        }
        passwordsignup.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                    onClick(buttonsignup);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
switch (view.getId()) {
    case R.id.buttonsignup:
        if (emailsignup.getText().toString().equals("") || usernamesignup.getText().toString().equals("") || passwordsignup.getText().toString().equals("")) {
            FancyToast.makeText(MainActivity.this, "All fields are compulsory", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }else {
            final ParseUser appuser = new ParseUser();
            appuser.setUsername(usernamesignup.getText().toString());
            appuser.setPassword(passwordsignup.getText().toString());
            appuser.setEmail(emailsignup.getText().toString());
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing Up " + usernamesignup.getText().toString());
            progressDialog.show();
            appuser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, "Signed Up Succesull  !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                    progressDialog.dismiss();
                }
            });
            transitiontosocialmediaAcivity();
        }
        break;
    case R.id.buttonloginmodule:
        Intent i = new Intent(MainActivity.this, loginactivity.class);
        startActivity(i);
        break;
}



    }
    public void layoutclicksignup(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transitiontosocialmediaAcivity(){
        Intent intent=new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
