package com.example.napp.data.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Post {
    private final String postId;
    private final String posterId;
    private final String fileUrl;
    private final Date timeCreated;
    private final List<String> like;
    private final List<Comment> comments;
    private String postText;

    public Post(String postId, String posterId, String fileUrl, Date timeCreated, List<String> like, List<Comment> comments, String postText) {
        this.postId = postId;
        this.posterId = posterId;
        this.fileUrl = fileUrl;
        this.timeCreated = timeCreated;
        this.like = like;
        this.comments = comments;
        this.postText = postText;
    }

    public List<Comment> getComments() {
        return comments;
    }
    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
    public String getPostId() {
        return postId;
    }

    public String getPosterId() {
        return posterId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public List<String> getLike() {
        return like;
    }






}
