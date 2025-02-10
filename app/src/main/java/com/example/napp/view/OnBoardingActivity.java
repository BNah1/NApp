package com.example.napp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.napp.MainActivity;
import com.example.napp.R;
import com.example.napp.adapter.ViewPageAdapter;
import com.example.napp.data.model.Onboading;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class OnBoardingActivity extends AppCompatActivity {

    private TextView txtSkip, txtNext;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ViewPageAdapter viewPageAdapter;
    private List<Onboading> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        init();

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                list
        );
        viewPager.setAdapter(viewPageAdapter);
        circleIndicator.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position ==2 ){
                    txtSkip.setText("GO");
                    txtNext.setVisibility(View.GONE);
                } else {
                    txtSkip.setText("Skip");
                    txtNext.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void ListOB(){
        list = new ArrayList<>();
        list.add(new Onboading(R.drawable.blaze,"Blaze","sadas asdsad asdasd"));
        list.add(new Onboading(R.drawable.buster,"Buster","sadas asdsad asdasd"));
        list.add(new Onboading(R.drawable.xenon_art,"Xenon","sadas asdsad asdasd"));
    }
    private void init(){
        txtSkip = findViewById(R.id.ob_txtSkip);
        txtNext = findViewById(R.id.ob_txtNext);
        viewPager = findViewById(R.id.ob_view_pager);
        circleIndicator = findViewById(R.id.ob_circle_indicator);

        ListOB();

        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() ==2){
                    Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    viewPager.setCurrentItem(2);
                }
            }
        });

        txtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = viewPager.getCurrentItem();
                if(i < 2){
                    viewPager.setCurrentItem(i+1);
                }
            }
        });

    }
}