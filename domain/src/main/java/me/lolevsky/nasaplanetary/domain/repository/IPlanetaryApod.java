package me.lolevsky.nasaplanetary.domain.repository;

import rx.Observable;

public interface IPlanetaryApod {
    Observable getPlanetaryApod();
}
