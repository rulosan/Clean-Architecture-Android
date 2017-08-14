package com.example.jhordan.euro_cleanarchitecture.data.local;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rulosan on 8/14/17.
 */

public interface AccessPointInterface {

    Observable<List<AccessPointEntity>> accessPointList();

    Observable<AccessPointEntity> accessPoint(final int id);
}
