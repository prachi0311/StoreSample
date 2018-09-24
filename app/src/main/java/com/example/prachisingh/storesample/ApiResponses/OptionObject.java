package com.example.prachisingh.storesample.ApiResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prachisingh on 21/09/18.
 */

public class OptionObject {
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("product_id")
    @Expose
    public long productId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("position")
    @Expose
    public int position;

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    @SerializedName("values")

    @Expose
    public ArrayList<String> values = null;
}
