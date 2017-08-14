package com.example.jhordan.euro_cleanarchitecture.data.repository;

import android.support.annotation.NonNull;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.AccessPointDataSource;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.AccessPointDataSourceFactory;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper.AccessPointToAccessPointEntity;
import com.example.jhordan.euro_cleanarchitecture.domain.model.AccessPoint;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccesPointRepositoryImp implements AccessPointRepository {

    private final AccessPointToAccessPointEntity mapperEntity;
    private final AccessPointDataSource dataSource;


    @Inject
    public AccesPointRepositoryImp(
            @NonNull AccessPointDataSourceFactory factory,
            @NonNull AccessPointToAccessPointEntity mapperEntity
            )
    {
        this.mapperEntity = mapperEntity;
        this.dataSource = factory.createDataSource();
    }

    @Override
    public Observable<List<AccessPoint>> accessPointList() {
        return dataSource.accessPointList().map(new Function<List<AccessPointEntity>, List<AccessPoint>>() {
            @Override
            public List<AccessPoint> apply(List<AccessPointEntity> accessPointEntities) throws Exception {
                return mapperEntity.reverseMap(accessPointEntities);
            }
        });
    }

    @Override
    public Observable<AccessPoint> accessPointById(int id) {
        return dataSource.accessPointById(id).map(new Function<AccessPointEntity, AccessPoint>() {
            @Override
            public AccessPoint apply(AccessPointEntity accessPointEntity) throws Exception {
                return mapperEntity.reverseMap(accessPointEntity);
            }
        });
    }
}
