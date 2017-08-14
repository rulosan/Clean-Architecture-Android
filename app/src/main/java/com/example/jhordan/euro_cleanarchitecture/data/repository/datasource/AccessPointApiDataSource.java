package com.example.jhordan.euro_cleanarchitecture.data.repository.datasource;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;
import com.example.jhordan.euro_cleanarchitecture.data.local.AccessPointInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointApiDataSource implements AccessPointDataSource{

    private AccessPointInterface apiInterface;

    public AccessPointApiDataSource(AccessPointInterface apiInterface)
    {
        this.apiInterface = apiInterface;
    }

    @Override
    public Observable<List<AccessPointEntity>> accessPointList() {
        return this.apiInterface.accessPointList();
    }

    @Override
    public Observable<AccessPointEntity> accessPointById(int id) {
        return this.apiInterface.accessPoint(id);
    }
}
