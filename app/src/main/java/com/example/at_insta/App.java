package com.example.at_insta;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("uEqZwxnPi6vRV9TxlVx2nRHF8XYecGm7X1Prepkl")
                // if defined
                .clientKey("iqe5H0UQvqsEAYsx2lut1g4c1Vbd5R6ay7WcLB89")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
