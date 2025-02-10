package com.example.napp.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.napp.data.model.Onboading;
import com.example.napp.view.fragment.Onboarding.OBFragment1;

import java.util.List;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private List<Onboading> list;
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior, List<Onboading> list ) {
        super(fm, behavior);
        this.list = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(list == null && list.isEmpty()){
            return null;
        }

        Onboading ob = list.get(position);
        OBFragment1 obFragment = new OBFragment1();
        Bundle bundle = new Bundle();
        bundle.putSerializable("OB",ob);
        obFragment.setArguments(bundle);

        return obFragment;
    }

    @Override
    public int getCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }
}
