package com.example.napp.data.model;

import java.time.LocalDateTime;
import java.util.List;

public class post {
    private final String postId;
    private final String posterId;
    private final String fileUrl;
    private final LocalDateTime timeCreated;

    private final List<String> like;


    public  post(String postId, String posterId, String fileUrl, LocalDateTime timeCreated, List<String> like) {
        this.postId = postId;
        this.posterId = posterId;
        this.fileUrl = fileUrl;
        this.timeCreated = timeCreated;
        this.like = like;
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

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public List<String> getLike() {
        return like;
    }






}
