package com.example.napp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.napp.R;
import com.example.napp.adapter.PostAdapter;
import com.example.napp.constant.Constant;
import com.example.napp.data.model.Post;
import com.example.napp.data.sql.TaskViewModelFactory;
import com.example.napp.databinding.FragmentCreationContainBinding;
import com.example.napp.databinding.FragmentNewFeedScreenBinding;
import com.example.napp.viewmodel.PostViewModel;
import com.example.napp.viewmodel.TaskViewModel;

import java.util.List;


public class NewFeedScreen extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private PostViewModel viewModel;


    public NewFeedScreen(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentNewFeedScreenBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_feed_screen, container, false);

        binding.setLifecycleOwner(getViewLifecycleOwner()); // Thiết lập lifecycle owner
        binding.setPostViewModel(viewModel); // Liên kết ViewModel
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        intit();

        //khoi tao recyclerview
        recyclerView = view.findViewById(R.id.new_feed_recyclerview_post_contain);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.hasFixedSize();

//        adapter = new PostAdapter(getContext(), Constant.getTestListPost());
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        viewModel.getList().observe(requireActivity(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                Log.d("RecyclerView", "Data changed : check - " + posts.size()); // Kiểm tra
                if (adapter == null) {
                    adapter = new PostAdapter(requireActivity(), posts);
                    recyclerView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void intit() {
        //khoi tao viewmodel
        viewModel = new PostViewModel(requireActivity());

        // Gán ViewModel vào DataBinding
        FragmentCreationContainBinding fragmentCreationContainBinding = DataBindingUtil.setContentView(requireActivity(),R.layout.fragment_creation_contain);
        fragmentCreationContainBinding.setPostViewModel(viewModel);
        fragmentCreationContainBinding.setLifecycleOwner(requireActivity());

    }

}