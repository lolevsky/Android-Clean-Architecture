package me.lolevsky.nasaplanetary.model.objects;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import me.lolevsky.nasaplanetary.data.net.request.PhotoComment;

@Parcel
public class MarsPhotoComments {
    int photoId;
    List<PhotoComment> comments = new ArrayList<>();

    public List<PhotoComment> getComments() {
        return comments;
    }

    public void setComments(List<PhotoComment> comments) {
        this.comments = comments;
    }

    public void addCommentt(PhotoComment comment) {
        comments.add(comment);
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
