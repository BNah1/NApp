package com.example.napp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.napp.view.fragment.MessageScreen;
import com.example.napp.view.fragment.NewFeedScreen;
import com.example.napp.view.fragment.ProfileScreen;
import com.example.napp.view.fragment.TaskScreen;

public class ViewPagerAdapterTab extends FragmentStatePagerAdapter {
    public ViewPagerAdapterTab(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return  new NewFeedScreen();
            case 2:
                return new MessageScreen();
            case 3:
                return  new TaskScreen();
            default:
                return new ProfileScreen();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
