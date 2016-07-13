package me.lolevsky.nasaplanetary.data.net.result.objects;

import com.google.gson.annotations.SerializedName;

public class Rover {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("landing_date")
    String landingDate;

    public String getLandingDate() {
        return landingDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
