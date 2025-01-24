package com.example.napp.view.fragment.Onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.napp.R;
import com.example.napp.data.model.Onboading;

public class OBFragment1 extends Fragment {
    private View view;
    private TextView txtTitle, txtDescription;
    private ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_o_b1, container, false);
        txtTitle = view.findViewById(R.id.ob1_text_title);
        txtDescription = view.findViewById(R.id.ob1_text_description);
        img = view.findViewById(R.id.ob1_img);

        Bundle bundleReceive = getArguments();
        if(bundleReceive != null){
            Onboading onboading = (Onboading) bundleReceive.get("OB");
            if (onboading != null){
                img.setImageResource(onboading.getImg());
                txtTitle.setText(onboading.getTile());
                txtDescription.setText(onboading.getDescription());
            }
        }
        return view;
    }
}