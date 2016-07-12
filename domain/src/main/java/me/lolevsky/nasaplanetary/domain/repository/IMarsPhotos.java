package me.lolevsky.nasaplanetary.domain.repository;

import rx.Observable;

public interface IMarsPhotos {
    Observable getMarsPhotos(String page);
}
