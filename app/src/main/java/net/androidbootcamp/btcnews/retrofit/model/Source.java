package net.androidbootcamp.btcnews.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("name")
    @Expose
    private String source;

    @SerializedName("id")
    @Expose
    private String id;

}
