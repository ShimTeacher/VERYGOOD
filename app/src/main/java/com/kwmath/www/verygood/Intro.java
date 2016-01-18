package com.kwmath.www.verygood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by adslbna2 on 2016. 1. 19..
 */
public class Intro extends AppIntro {

    public static Activity AActivity;

    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.fragment_first_slide));
        addSlide(SampleSlide.newInstance(R.layout.fragment_second_slide));
        addSlide(SampleSlide.newInstance(R.layout.fragment_third_slide));
        addSlide(SampleSlide.newInstance(R.layout.fragment_four_slide));

    }

    private void loadMainActivity(){
        AActivity = Intro.this;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNextPressed() {
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
