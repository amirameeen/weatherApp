package com.rajawali.weatherapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by macbookultimate on 12/27/17.
 */

public class Rain {
    @SerializedName("3h")
    @Expose
    private String _3h;

    public String get3h() {
        return _3h;
    }

    public void set3h(String _3h) {
        this._3h = _3h;
    }
}
