package me.lolevsky.nasaplanetary.mapper;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import me.lolevsky.nasaplanetary.data.net.result.ApodResponse;
import me.lolevsky.nasaplanetary.model.ApodModel;

public class ApodModelDataMapper implements IModelDataMapper<ApodResponse, ApodModel>{
    @Inject
    public ApodModelDataMapper() {}

    @Override
    public ApodModel transform(ApodResponse apodResponse) {
        Preconditions.checkNotNull(apodResponse);

        ApodModel apodModel = new ApodModel();
        apodModel.setDate(apodResponse.getDate());
        apodModel.setExplanation(apodResponse.getExplanation());
        apodModel.setHdurl(apodResponse.getHdurl());
        apodModel.setMedia_type(apodResponse.getMedia_type());
        apodModel.setService_version(apodResponse.getService_version());
        apodModel.setTitle(apodResponse.getTitle());
        apodModel.setUrl(apodResponse.getUrl());
        apodModel.setCopyright(apodResponse.getCopyright());

        return apodModel;
    }
}
