package com.example.jhordan.euro_cleanarchitecture.view.viewmodel.mapper;

import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper.Mapper;
import com.example.jhordan.euro_cleanarchitecture.domain.model.AccessPoint;
import com.example.jhordan.euro_cleanarchitecture.view.viewmodel.AccessPointViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rulosan on 8/14/17.
 */
@Singleton
public class AccessPointViewModelToAccessPointMapper extends Mapper<AccessPointViewModel,AccessPoint> {

    @Inject
    public AccessPointViewModelToAccessPointMapper(){

    }
    @Override
    public AccessPoint map(AccessPointViewModel value) {
        AccessPoint entity = new AccessPoint();
        entity.setId(value.getId());
        entity.setCypher_mode(value.getCypher_mode());
        entity.setInsert_timestamp(value.getInsert_timestamp());
        entity.setLatitude(value.getLatitude());
        entity.setLongitude(value.getLongitude());
        entity.setMacaddress(value.getMacaddress());
        entity.setSsid(value.getSsid());
        entity.setUpdate_timestamp(value.getUpdate_timestamp());
        return entity;
    }

    @Override
    public AccessPointViewModel reverseMap(AccessPoint value) {
        AccessPointViewModel model = new AccessPointViewModel();
        model.setId(value.getId());
        model.setCypher_mode(value.getCypher_mode());
        model.setInsert_timestamp(value.getInsert_timestamp());
        model.setLatitude(value.getLatitude());
        model.setLongitude(value.getLongitude());
        model.setMacaddress(value.getMacaddress());
        model.setSsid(value.getSsid());
        model.setUpdate_timestamp(value.getUpdate_timestamp());
        return model;
    }
}
