package me.lolevsky.nasaplanetary.domain.tracking;

import me.lolevsky.nasaplanetary.domain.remoteconfig.IRemoteConfig;

public interface ITracking {
    void LogEventScreen(String eventName);
    void LogEventClick(String eventName);
    void LogEvent(String category, String eventName);
    void LogException(String exception);
    void setUserProperty(String experimentName, IRemoteConfig.ExperimentVariant experimentVariant);
}
