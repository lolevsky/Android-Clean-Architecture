package me.lolevsky.nasaplanetary.mapper;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.ApodResponse;
import me.lolevsky.nasaplanetary.domain.entety.MainScreenEntity;
import me.lolevsky.nasaplanetary.model.ApodModel;
import me.lolevsky.nasaplanetary.model.MainScreenModule;
import me.lolevsky.nasaplanetary.model.objects.MainScreen;

public class MainScreenModelDataMapper implements IModelDataMapper<List<MainScreenEntity>, MainScreenModule>{
    @Inject
    public MainScreenModelDataMapper() {}

    @Override
    public MainScreenModule transform(List<MainScreenEntity> screenEntities) {
        Preconditions.checkNotNull(screenEntities);

        MainScreenModule mainScreenModule = new MainScreenModule();

        for(MainScreenEntity mainScreenEntity : screenEntities){
            mainScreenModule.setAddItem(new MainScreen(mainScreenEntity.getName(), mainScreenEntity.getImageUrl()));
        }

        return mainScreenModule;
    }
}
