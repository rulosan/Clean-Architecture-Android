package com.example.jhordan.euro_cleanarchitecture.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.jhordan.euro_cleanarchitecture.data.local.AccessPointWebImp;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper.AccessPointEntityJsonMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rulosan on 8/14/17.
 */

@Singleton
public class AccessPointDataSourceFactory {

    private final Context context;

    @Inject
    public AccessPointDataSourceFactory(@NonNull Context context)
    {
        this.context = context;
    }

    public AccessPointDataSource createDataSource(){
        AccessPointEntityJsonMapper jsonMapper = new AccessPointEntityJsonMapper();
        AccessPointWebImp webApiImp = new AccessPointWebImp(context, jsonMapper);
        return new AccessPointApiDataSource(webApiImp);
    }



}
