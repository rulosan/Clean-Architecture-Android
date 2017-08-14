package com.example.jhordan.euro_cleanarchitecture.data.repository.datasource;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rulosan on 8/14/17.
 */

public interface AccessPointDataSource {


    Observable<List<AccessPointEntity>> accessPointList();

    Observable<AccessPointEntity> accessPointById(int id);

}
