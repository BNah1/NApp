package com.example.napp.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.napp.constant.Constant;
import com.example.napp.data.model.Comment;
import com.example.napp.data.model.Post;
import com.example.napp.data.model.UserProfile;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostViewModel extends ViewModel {
    private MutableLiveData<String> text;
    private MutableLiveData<List<Post>> listPostLive;
    private List<Post> listPost;
    private UserProfile userProfile;

    public PostViewModel() {
        text = new MutableLiveData<>();
        listPostLive = new MutableLiveData<>();
        initData();
    }

    public MutableLiveData<String> getText() {
        return text;
    }

    private void initData(){
        userProfile = Constant.testUser;
        listPost = Constant.getTestListPost();
        Log.d("DEBUG", "ViewModel: " + "da chay init data");
        listPostLive.setValue(listPost);
    }

    public MutableLiveData<List<Post>> getList() {
        return listPostLive;
    }

    public void setList(MutableLiveData<List<Post>> list) {
        this.listPostLive = list;
    }

    public void addPost(){
        String inputText = text.getValue();
        Post post = new Post("111",userProfile.getUid(), "link",new Date(),new ArrayList<String>(),new ArrayList<Comment>(),inputText);
        listPost.add(0,post);
        text.setValue("");
        Log.d("d",post.getPostText()+" "+post.getTimeCreated());
        listPostLive.setValue(listPost);
        Log.d("RecyclerView", "Size of List of Post is(trong VM) : " + listPost.size());
    }
}
