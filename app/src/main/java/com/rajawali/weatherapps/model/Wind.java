package com.rajawali.weatherapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbookultimate on 12/27/17.
 */

public class Wind {
    @SerializedName("speed")
    @Expose
    private String speed;
    @SerializedName("deg")
    @Expose
    private String deg;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }
}
