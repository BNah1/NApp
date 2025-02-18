package com.example.napp.data.model;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {
    private final String authorId;
    private final String postId;
    private final String cmtId;
    private final LocalDateTime time;
    private final List<String> likes;

    public Comment(String authorId, String postId, String cmtId, LocalDateTime time, List<String> likes) {
        this.authorId = authorId;
        this.postId = postId;
        this.cmtId = cmtId;
        this.time = time;
        this.likes = likes;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getPostId() {
        return postId;
    }

    public String getCmtId() {
        return cmtId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public List<String> getLikes() {
        return likes;
    }
}
