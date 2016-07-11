package me.lolevsky.nasaplanetary.mapper;

public interface IModelDataMapper<T, M> {
    M transform(T response);
}
