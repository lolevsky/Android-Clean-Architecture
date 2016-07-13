package me.lolevsky.nasaplanetary.mapper;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.MarsPhotosResponse;
import me.lolevsky.nasaplanetary.data.net.result.objects.Photo;
import me.lolevsky.nasaplanetary.model.MarsPhotosModel;
import me.lolevsky.nasaplanetary.model.objects.MarsPhoto;

public class MarsPhotosModelDataMapper implements IModelDataMapper<MarsPhotosResponse, MarsPhotosModel> {
    @Inject
    public MarsPhotosModelDataMapper() {
    }

    @Override
    public MarsPhotosModel transform(MarsPhotosResponse marsPhotosResponses) {
        Preconditions.checkNotNull(marsPhotosResponses);

        MarsPhotosModel marsPhotosModel = new MarsPhotosModel();
        if (marsPhotosResponses.getPhotos() == null) {
            return marsPhotosModel;
        }

        MarsPhoto marsPhoto = null;

        if (marsPhotosResponses.getPhotos().size() > 0) {
            for (Photo photo : marsPhotosResponses.getPhotos()) {
                marsPhoto = new MarsPhoto();

                marsPhoto.setEarthDate(photo.getEarthDate());
                marsPhoto.setId(photo.getId());
                marsPhoto.setImgSrc(photo.getImgSrc());

                if (photo.getCamera() != null) {
                    marsPhoto.setCameraFullName(photo.getCamera().getFullName());
                    marsPhoto.setCameraName(photo.getCamera().getName());
                }

                if (photo.getRover() != null) {
                    marsPhoto.setRoverId(photo.getRover().getId());
                    marsPhoto.setRoverLandingDate(photo.getRover().getLandingDate());
                    marsPhoto.setRoverName(photo.getRover().getName());
                }

                marsPhotosModel.getMarsPhotos().add(marsPhoto);
            }
        }
        return marsPhotosModel;
    }
}
