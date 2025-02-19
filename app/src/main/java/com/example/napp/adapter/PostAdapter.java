package com.example.napp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.napp.R;
import com.example.napp.constant.Constant;
import com.example.napp.data.model.Post;
import com.example.napp.databinding.ItemPostBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    Context context;
    List<Post> listPost;

    public PostAdapter(Context context, List<Post> listPost) {
        this.context = context;
        this.listPost = listPost;
    }




    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding itemPostBinding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PostViewHolder(itemPostBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = listPost.get(position);
        if(post == null){
            return;
        }
        holder.Bind(post);

    }

    @Override
    public int getItemCount() {
        if(listPost != null){
            return listPost.size();
        }
        return 0;
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        private final ItemPostBinding  itemPostBinding;

        public PostViewHolder(@NonNull ItemPostBinding postContainBinding) {
            super(postContainBinding.getRoot());

            this.itemPostBinding = postContainBinding;
        }


        private void Bind(Post post){
            //Text
            itemPostBinding.postTxt.setText(post.getPostText());
            itemPostBinding.postTxtNamePoster.setText(post.getPosterId());
            itemPostBinding.postTxtCreatedTime.setText(post.getTimeCreated().toString());
            itemPostBinding.postTxtFavouriteNumbder.setText(String.valueOf(post.getLike().size()));
            itemPostBinding.postTxtCommentNumber.setText(String.valueOf(post.getComments().size()));

            //image
            itemPostBinding.postViewAvatar.setImageResource(Constant.testAvatar);
            itemPostBinding.postImage.setImageResource(Constant.testImage);

            //image button
            itemPostBinding.postViewComment.setImageResource(R.drawable.icon_message);
            itemPostBinding.postViewFavourite.setImageResource(R.drawable.icon_heart);

        }

    }
}
