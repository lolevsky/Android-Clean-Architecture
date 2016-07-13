package me.lolevsky.nasaplanetary.data.net.result.objects;

import com.google.gson.annotations.SerializedName;

public class Camera {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("rover_id")
    int roverId;
    @SerializedName("full_name")
    String fullName;

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoverId() {
        return roverId;
    }
}
