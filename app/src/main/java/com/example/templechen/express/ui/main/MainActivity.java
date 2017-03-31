package com.example.templechen.express.ui.main;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.templechen.express.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTransition();
    }

    @TargetApi(21)
    private void setTransition() {
        Explode explode = new Explode();
        explode.setDuration(500);
        explode.setInterpolator(new AccelerateDecelerateInterpolator());
        getWindow().setExitTransition(explode);

        Slide slide = new Slide();
        slide.setDuration(500);
        slide.setInterpolator(new AccelerateDecelerateInterpolator());
        getWindow().setEnterTransition(slide);

//        Fade fade = new Fade();
    }
}
