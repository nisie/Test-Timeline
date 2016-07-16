package com.nisieapp.nis.testtimeline;

import java.util.Date;

/**
 * Created by Nisie on 7/2/2016.
 */
public class Timeline {

    String shopId;
    String context;
    String imgUrl;
    int type;
    Date created;
    Date updated;
    Boolean isDeleted;
    private String objectId;

    public Timeline() {

    }


    public String getObjectId()
    {
        return objectId;
    }

    public void setObjectId( String objectId )
    {
        this.objectId = objectId;
    }

    public Timeline(int type) {
        this.type = type;
    }

    public Timeline(int type, String shopId, String context) {
        this.type = type;
        this.shopId = shopId;
        this.context = context;
        this.imgUrl = "";
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
