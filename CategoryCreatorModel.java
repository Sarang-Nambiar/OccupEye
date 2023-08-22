package com.example.occupeye;

public class CategoryCreatorModel {
    String roomName;
    int image;
    String colour;
    String users;
    String room_cap;
    String roomType;

    public CategoryCreatorModel(String roomName, int image, String colour, String roomType,String users,String room_cap) {
        this.roomName = roomName;
        this.image = image;
        this.colour = colour;
        this.roomType = roomType;
        this.users=users;
        this.room_cap=room_cap;
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
    public String getRoom_cap(){
        return room_cap;
    }
    public String getUsers(){return  users;}

    public String getRoomType(){
        return roomType;
    }
}
