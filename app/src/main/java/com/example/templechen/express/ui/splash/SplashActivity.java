package com.example.templechen.express.ui.splash;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.templechen.express.MainActivity;
import com.example.templechen.express.R;
import com.example.templechen.express.util.SettingUtil;

/**
 * Created by templechen on 2017/3/31.
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager viewPager;
    private ImageButton preButton, nextButton;
    private AppCompatButton goToMainActivityButton;
    private ImageView[] indicators;
    private int currentIndex = 0;
    private int[] colors;
    private static final String TAG = "SplashActivity";
    private boolean isFirstLaunch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        isFirstLaunch = sp.getBoolean(SettingUtil.FIRST_LAUCH, true);
        if (isFirstLaunch){
            setContentView(R.layout.activity_splash);
            initDatas();
            initViews();
        }else {
            goToMainActivity();
        }

    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.splash_viewpager);
        viewPager.setBackgroundColor(colors[currentIndex]);
        preButton = (ImageButton) findViewById(R.id.splash_pre_btn);
        nextButton = (ImageButton) findViewById(R.id.splash_next_btn);
        preButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        goToMainActivityButton = (AppCompatButton) findViewById(R.id.splash_sure_btn);
        goToMainActivityButton.setOnClickListener(this);
        indicators = new ImageView[]{(ImageView) findViewById(R.id.splash_indicator_first),
                (ImageView) findViewById(R.id.splash_indicator_second),
                (ImageView) findViewById(R.id.splash_indicator_third),
        };
        SplashViewPagerAdapter adapter = new SplashViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int updateColor;
                updateColor = (int) new ArgbEvaluator().evaluate(positionOffset, colors[position], colors[position == 2?position:position+1]);
                updateBackgroundColor(updateColor);
            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                setIndicatorColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setIndicatorColor(int position) {
        for (int i=0; i<indicators.length;i++){
            if (i == position){
                indicators[i].setImageResource(R.drawable.ic_indicator_selected);
            }else {
                indicators[i].setImageResource(R.drawable.ic_indicator_unselected);
            }
        }
        if (position == indicators.length -1){
            goToMainActivityButton.setVisibility(View.VISIBLE);
        }else {
            goToMainActivityButton.setVisibility(View.GONE);
        }
    }

    private void updateBackgroundColor(int color){
        viewPager.setBackgroundColor(color);
    }

    private void initDatas() {
        colors = new int[]{getColor(R.color.colorPrimary), getColor(R.color.colorPrimaryDark), getColor(R.color.colorAccent)};
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: " + v.getId());
        switch (v.getId()){
            case R.id.splash_pre_btn:
                currentIndex -= 1;
                viewPager.setCurrentItem(currentIndex, true);
                break;
            case R.id.splash_next_btn:
                currentIndex += 1;
                viewPager.setCurrentItem(currentIndex, true);
                break;
            case R.id.splash_sure_btn:
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean(SettingUtil.FIRST_LAUCH, false);
                editor.apply();
                goToMainActivity();
            default:
                break;
        }
    }

    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}
