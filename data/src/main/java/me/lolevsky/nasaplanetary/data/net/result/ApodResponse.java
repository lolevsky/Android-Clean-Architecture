package me.lolevsky.nasaplanetary.data.net.result;

import com.google.gson.annotations.SerializedName;

public class ApodResponse {
    @SerializedName("date")
    String date;
    @SerializedName("explanation")
    String explanation;
    @SerializedName("hdurl")
    String hdurl;
    @SerializedName("media_type")
    String media_type;
    @SerializedName("service_version")
    String service_version;
    @SerializedName("title")
    String title;
    @SerializedName("url")
    String url;
    @SerializedName("copyright")
    String copyright;

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
