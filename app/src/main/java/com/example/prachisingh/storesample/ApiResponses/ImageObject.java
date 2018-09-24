package com.example.prachisingh.storesample.ApiResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prachisingh on 21/09/18.
 */

public class ImageObject {
    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public Object getAlt() {
        return alt;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getSrc() {
        return src;
    }

    @SerializedName("id")

    @Expose
    public long id;
    @SerializedName("product_id")
    @Expose
    public long productId;
    @SerializedName("alt")
    @Expose
    public Object alt;
    @SerializedName("width")
    @Expose
    public int width;
    @SerializedName("height")
    @Expose
    public int height;
    @SerializedName("src")
    @Expose
    public String src;
}
