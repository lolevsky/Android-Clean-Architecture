package me.lolevsky.nasaplanetary.domain.remoteconfig;

public interface IRemoteConfig {
    static final String EXPERIMENT_HOME_SCREEN_ABOUT_MENU = "home_screen_about_menu";

    enum ExperimentVariant{
        NONE, VARIANT_A, VARIANT_B
    }

    ExperimentVariant getExperimentVariant(String key);
}
