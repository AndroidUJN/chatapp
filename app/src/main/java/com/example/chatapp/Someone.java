package com.example.chatapp;

/**
 * Created by kuiye on 2018/2/26.
 */

public class Someone {
    private  String name;
    private  int imageId;
    public Someone(String name, int imageId) {
        this.name=name;
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
