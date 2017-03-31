package com.example.templechen.express.ui.splash;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.templechen.express.R;

/**
 * Created by templechen on 2017/3/31.
 */

public class SplashFragment extends Fragment {

    private int page;
    private ImageView splash_image;
    private AppCompatTextView splash_title;
    private AppCompatTextView splash_desc;
    private static final String ARG_SECTION_NUM = "section_number";

    public static SplashFragment newInstance(int page){
        SplashFragment splashFragment = new SplashFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUM, page);
        splashFragment.setArguments(bundle);
        return splashFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_SECTION_NUM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        initViews(view);
        switch (page){
            case 0:
                splash_image.setBackgroundResource(R.drawable.ic_splash_beenhere);
                splash_title.setText(R.string.splash_first_page_title);
                splash_desc.setText(R.string.splash_first_page_desc);
                break;
            case 1:
                splash_image.setBackgroundResource(R.drawable.ic_splash_notification);
                splash_title.setText(R.string.splash_second_page_title);
                splash_desc.setText(R.string.splash_second_page_desc);
                break;
            case 2:
                splash_image.setBackgroundResource(R.drawable.ic_splash_watch);
                splash_title.setText(R.string.splash_third_page_title);
                splash_desc.setText(R.string.splash_third_page_desc);
                break;
            default:
                break;
        }
        return view;
    }

    private void initViews(View view) {
        splash_image = (ImageView) view.findViewById(R.id.splash_fragment_image);
        splash_title = (AppCompatTextView) view.findViewById(R.id.splash_fragment_title);
        splash_desc = (AppCompatTextView) view.findViewById(R.id.splash_fragment_desc);
    }
}
