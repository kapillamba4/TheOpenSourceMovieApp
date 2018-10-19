package com.kapillamba4.thecompletemovieguide.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kapil on 4/11/17.
 */

public class ResourceImage {
    @SerializedName("file_path")
    @Expose
    String filePath;
    @SerializedName("height")
    @Expose
    String height;
    @SerializedName("width")
    @Expose
    String width;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
