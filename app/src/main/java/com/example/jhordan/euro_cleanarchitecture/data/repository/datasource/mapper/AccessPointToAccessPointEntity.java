package com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;
import com.example.jhordan.euro_cleanarchitecture.domain.model.AccessPoint;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointToAccessPointEntity extends Mapper<AccessPoint, AccessPointEntity> {


    @Override
    public AccessPointEntity map(AccessPoint value) {
        AccessPointEntity entity = new AccessPointEntity();
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
    public AccessPoint reverseMap(AccessPointEntity value) {
        AccessPoint model = new AccessPoint();
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
