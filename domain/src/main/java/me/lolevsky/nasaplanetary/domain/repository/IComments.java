package me.lolevsky.nasaplanetary.domain.repository;

import rx.Observable;

public interface IComments {
    Observable getComments(String photoId);

    Observable sendComment(String photoId, String message);
}
