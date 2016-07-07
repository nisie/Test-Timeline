package com.nisieapp.nis.testtimeline;

/**
 * Created by Nisie on 7/2/2016.
 */
public class Timeline {

    int id;
    String time;
    String poster;
    String avatarUrl;
    String context;
    String imgUrl;
    String productUrl;
    int type;

    public Timeline(int type) {
        this.type = type;
    }

    public Timeline(int type, String poster, String context) {
        this.type = type;
        this.poster = poster;
        this.context = context;
        this.imgUrl = "";
    }
}
