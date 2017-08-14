package com.example.jhordan.euro_cleanarchitecture.data.repository.datasource;

import android.support.annotation.NonNull;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;
import com.example.jhordan.euro_cleanarchitecture.data.entity.TeamEntity;
import com.example.jhordan.euro_cleanarchitecture.data.local.ApiInterface;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointApiDataSource implements AccessPointDataSource{

    private ApiInterface apiInterface;

    public AccessPointApiDataSource(@NonNull ApiInterface apiInterface)
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
