package com.example.prachisingh.storesample.ApiResponses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prachisingh on 21/09/18.
 */

public class ProductObject {
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("vendor")
    @Expose
    public String vendor;
    @SerializedName("product_type")
    @Expose
    public String productType;

    @SerializedName("handle")
    @Expose
    public String handle;

    @SerializedName("tags")
    @Expose
    public String tags;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVendor() {
        return vendor;
    }

    public String getProductType() {
        return productType;
    }

    public String getHandle() {
        return handle;
    }

    public String getTags() {
        return tags;
    }

    public ArrayList<OptionObject> getOptions() {
        return options;
    }

    public ImageObject getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }



    @SerializedName("options")

    @Expose
    public ArrayList<OptionObject> options ;

    public ArrayList<VariantObject> getVariants() {
        return variants;
    }

    @SerializedName("image")
    @Expose
    public ImageObject image;

    @SerializedName("inventory_quantity")
    @Expose
    public int quantity;

    @SerializedName("variants")
    @Expose
    public ArrayList<VariantObject> variants;



}
