package me.lolevsky.nasaplanetary.mapper;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.R;
import me.lolevsky.nasaplanetary.domain.entety.MainScreenEntity;
import me.lolevsky.nasaplanetary.model.MainScreenModule;
import me.lolevsky.nasaplanetary.model.objects.MainScreen;

public class MainScreenModelDataMapper implements IModelDataMapper<List<MainScreenEntity>, MainScreenModule>{
    @Inject
    public MainScreenModelDataMapper() {}

    @Override
    public MainScreenModule transform(List<MainScreenEntity> screenEntities) {
        Preconditions.checkNotNull(screenEntities);

        MainScreenModule mainScreenModule = new MainScreenModule();

        for(int i = 0 ; i < screenEntities.size() ; i ++){
            mainScreenModule.setAddItem(new MainScreen(screenEntities.get(i).getName(), i == 0 ? R.drawable.item_2 : R.drawable.item_1));
        }

        return mainScreenModule;
    }
}
