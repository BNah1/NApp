package com.example.napp.data.model;

import java.io.Serializable;

public class Onboading implements Serializable {
    private int img;
    private String tile;
    private String description;

    public Onboading(int img, String tile, String description) {
        this.img = img;
        this.tile = tile;
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
