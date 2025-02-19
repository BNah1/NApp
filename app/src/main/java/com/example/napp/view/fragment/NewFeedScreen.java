package com.example.napp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.napp.R;
import com.example.napp.adapter.PostAdapter;
import com.example.napp.constant.Constant;


public class NewFeedScreen extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter adapter;


    public NewFeedScreen(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_feed_screen, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.new_feed_recyclerview_post_contain);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();
        adapter = new PostAdapter(getContext(), Constant.getTestListPost());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}