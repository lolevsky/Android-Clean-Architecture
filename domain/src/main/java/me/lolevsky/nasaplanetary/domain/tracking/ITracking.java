package me.lolevsky.nasaplanetary.domain.tracking;

public interface ITracking {
    void LogEventScreen(String eventName);
    void LogEventClick(String eventName);
    void LogEvent(String category, String eventName);
    void LogException(String exception);
}
