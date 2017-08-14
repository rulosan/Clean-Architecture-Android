package com.example.jhordan.euro_cleanarchitecture.domain.usecase;


import com.example.jhordan.euro_cleanarchitecture.data.repository.AccessPointRepository;
import com.example.jhordan.euro_cleanarchitecture.domain.model.AccessPoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by rulosan on 8/14/17.
 */

public class GetAccessPoints extends UseCase<List<AccessPoint>> {

    private final AccessPointRepository repository;

    @Inject
    public GetAccessPoints(
            @Named("executor_thread")
            Scheduler executorThread,
            @Named("ui_thread")
            Scheduler uiThread,
            AccessPointRepository accessPointRepository
    )
    {
        super(executorThread,uiThread);
        this.repository = accessPointRepository;

    }

    @Override
    protected Observable<List<AccessPoint>> createObservableUseCase() {
        return this.repository.accessPointList();
    }
}
