package com.hunky;

/**
 * Created by Hunky on 06.11.2015.
 */
public class Post {
    int id;
    String txt;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", txt='" + txt + '\'' +
                '}';
    }

    public Post() {}

    public Post(int id, String txt) {
        this.id = id;
        this.txt = txt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }




}
