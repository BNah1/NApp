package com.example.napp.data.model;

import java.util.UUID;

public class Task {
    private int image;
    private String name;
    private String description;
    private String id;




    private int position;

    public Task(int image, String name, String description, int position, String id) {
        this.id = id != null ? id : UUID.randomUUID().toString(); // tu dong tao id duy nhat
        this.image = image;
        this.name = name;
        this.description = description;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
