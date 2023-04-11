package com.example.occupeye;

public class CategoryCreatorModel {
    String roomName;
    int image;
    String colour;

    public CategoryCreatorModel(String roomName, int image, String colour) {
        this.roomName = roomName;
        this.image = image;
        this.colour = colour;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getColour() {
        return colour;
    }

    public int getImage() {
        return image;
    }
}
