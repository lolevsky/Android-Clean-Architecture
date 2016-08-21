package me.lolevsky.nasaplanetary.domain.remoteconfig;

public interface IRemoteConfig {
    void init(boolean isDebug);

    boolean getBoolean(String key);
}
