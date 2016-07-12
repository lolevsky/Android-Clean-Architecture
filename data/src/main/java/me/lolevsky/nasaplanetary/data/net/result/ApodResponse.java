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
    String mediaType;
    @SerializedName("service_version")
    String serviceVersion;
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

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
