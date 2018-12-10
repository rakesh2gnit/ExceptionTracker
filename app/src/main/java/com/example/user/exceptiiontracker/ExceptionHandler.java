package com.example.user.exceptiiontracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rollbar.android.Rollbar;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;
import main.java.com.mindscapehq.android.raygun4android.RaygunClient;

public class ExceptionHandler extends AppCompatActivity {

    private String from, test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        from = getIntent().getStringExtra("from");

        if (from.equalsIgnoreCase("firebase")) {
            Log.e("Firebase", test);
        } else if (from.equalsIgnoreCase("sentry")) {
            logWithStaticAPI();
        } else if (from.equalsIgnoreCase("rollbar")) {
            Rollbar.init(this);
            Rollbar.instance().error(new Exception("This is a test error")); //remove this after initial testing
        } else {//For raygun
            RaygunClient.init(getApplicationContext());
            RaygunClient.attachExceptionHandler();
        }
    }

    void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }

    void logWithStaticAPI() {

        Sentry.getContext().recordBreadcrumb(
                new BreadcrumbBuilder().setMessage("User made an action").build()
        );

        Sentry.getContext().setUser(
                new UserBuilder().setEmail("hello@sentry.io").build()
        );

        Sentry.capture("This is a test");

        try {
            unsafeMethod();
        } catch (Exception e) {
            // This sends an exception event to Sentry using the statically stored instance
            // that was created in the ``main`` method.
            Sentry.capture(e);
        }
    }
}
