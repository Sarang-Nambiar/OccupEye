package com.example.occupeye;

public class CategoryCreatorModel {
    String roomName;
    int image;

    public CategoryCreatorModel(String roomName, int image) {
        this.roomName = roomName;
        this.image = image;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getImage() {
        return image;
    }
}
