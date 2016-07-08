package com.nisieapp.nis.testtimeline;

import java.util.Date;

/**
 * Created by Nisie on 7/2/2016.
 */
public class Timeline {

    String userId;
    String context;
    String imgUrl;
    int type;
    private Date created;
    private Date updated;

    public Timeline() {

    }

    public Timeline(int type) {
        this.type = type;
    }

    public Timeline(int type, String userId, String context) {
        this.type = type;
        this.userId = userId;
        this.context = context;
        this.imgUrl = "";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated( Date created )
    {
        this.created = created;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated( Date updated )
    {
        this.updated = updated;
    }
}
