package com.example.walkeraki.application;

/**
 * Created by walkeraki on 17.11.2015.
 */
public class MyPojo {
    String title;
    String describtion;
    public MyPojo(String title,String describtion)
    {
        this.title=title;
        this.describtion=describtion;
    }

    public String getTitle(){
        return title;
    }
    public String getDescribtion(){
        return describtion;
    }
}
