package com.example.prachisingh.storesample.ApiResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prachisingh on 23/09/18.
 */

public class VariantObject {
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("inventory_quantity")
    @Expose
    public int inventoryQuantity;

    public long getId() {
        return id;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public String getTitle() {
        return title;
    }

    @SerializedName("title")
    @Expose

    public String title;
}
