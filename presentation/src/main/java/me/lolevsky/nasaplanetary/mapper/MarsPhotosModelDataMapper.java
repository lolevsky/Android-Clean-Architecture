package me.lolevsky.nasaplanetary.mapper;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.MarsPhotosResponse;
import me.lolevsky.nasaplanetary.model.MarsPhotosModel;
import me.lolevsky.nasaplanetary.model.objects.MarsPhoto;

public class MarsPhotosModelDataMapper implements IModelDataMapper<List<MarsPhotosResponse>, MarsPhotosModel>{
    @Inject
    public MarsPhotosModelDataMapper() {}

    @Override
    public MarsPhotosModel transform(List<MarsPhotosResponse> marsPhotosResponses) {
        Preconditions.checkNotNull(marsPhotosResponses);

        MarsPhotosModel marsPhotosModel = new MarsPhotosModel();
        MarsPhoto marsPhoto = null;

        if(marsPhotosResponses.size() > 0){
            for(MarsPhotosResponse marsPhotosResponse : marsPhotosResponses){
                marsPhoto = new MarsPhoto();

                marsPhoto.setEarthDate(marsPhotosResponse.getEarthDate());
                marsPhoto.setId(marsPhotosResponse.getId());
                marsPhoto.setImgSrc(marsPhotosResponse.getImgSrc());

                if(marsPhotosResponse.getCamera() != null){
                    marsPhoto.setCameraFullName(marsPhotosResponse.getCamera().getFullName());
                    marsPhoto.setCameraName(marsPhotosResponse.getCamera().getName());
                }

                if(marsPhotosResponse.getRover() != null){
                    marsPhoto.setRoverId(marsPhotosResponse.getRover().getId());
                    marsPhoto.setRoverLandingDate(marsPhotosResponse.getRover().getLandingDate());
                    marsPhoto.setRoverName(marsPhotosResponse.getRover().getLandingDate());
                }

                marsPhotosModel.getMarsPhotos().add(marsPhoto);
            }
        }
        return marsPhotosModel;
    }
}
