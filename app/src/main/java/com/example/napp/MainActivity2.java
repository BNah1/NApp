package com.example.napp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.napp.adapter.ViewPagerAdapterTab;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView bottomNavigationView;
    private ViewPagerAdapterTab viewPagerAdapterTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        init();

    }

    private void init(){
        mViewPager = findViewById(R.id.main_view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        viewPagerAdapterTab = new ViewPagerAdapterTab(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapterTab);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                    break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.nav_newfeed).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.nav_message).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.nav_task).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_home) {
                    mViewPager.setCurrentItem(0);
                } else if (menuItem.getItemId() == R.id.nav_newfeed) {
                    mViewPager.setCurrentItem(1);
                } else if (menuItem.getItemId() == R.id.nav_message) {
                    mViewPager.setCurrentItem(2);
                } else if (menuItem.getItemId() == R.id.nav_task) {
                    mViewPager.setCurrentItem(3);
                } else {
                    return false;
                }
                return true;
            }
        });

    }


}