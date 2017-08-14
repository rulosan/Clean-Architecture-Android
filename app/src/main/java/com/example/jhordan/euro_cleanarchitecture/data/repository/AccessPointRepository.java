package com.example.jhordan.euro_cleanarchitecture.data.repository;

import com.example.jhordan.euro_cleanarchitecture.domain.model.AccessPoint;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rulosan on 8/14/17.
 */

public interface AccessPointRepository
{
    Observable<List<AccessPoint>> accessPointList();
    Observable<AccessPoint> accessPointById(final int id);
}
