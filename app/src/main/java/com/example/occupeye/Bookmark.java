package com.example.occupeye;

import java.util.ArrayList;

public class Bookmark {
    private static Bookmark  bookmark= null;

    public static ArrayList<CategoryCreatorModel> getBookmarkedLocs() {
        return bookmarkedLocs;
    }



    static ArrayList<CategoryCreatorModel> bookmarkedLocs;

    private Bookmark(){
        bookmarkedLocs=new ArrayList<>();
    }
    public static Bookmark getBookmark(){
        if(bookmark==null){
            new Bookmark();
        }
        return bookmark;
    }
}
