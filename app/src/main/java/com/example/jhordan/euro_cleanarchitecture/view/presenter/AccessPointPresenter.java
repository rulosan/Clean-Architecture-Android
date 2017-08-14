package com.example.jhordan.euro_cleanarchitecture.view.presenter;

import android.support.annotation.NonNull;

import com.example.jhordan.euro_cleanarchitecture.domain.model.AccessPoint;
import com.example.jhordan.euro_cleanarchitecture.domain.usecase.GetAccessPoints;
import com.example.jhordan.euro_cleanarchitecture.domain.usecase.UseCaseObserver;
import com.example.jhordan.euro_cleanarchitecture.view.viewmodel.AccessPointViewModel;
import com.example.jhordan.euro_cleanarchitecture.view.viewmodel.mapper.AccessPointViewModelToAccessPointMapper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointPresenter extends Presenter<AccessPointPresenter.View> {

    public GetAccessPoints getAccessPoints;
    public AccessPointViewModelToAccessPointMapper mapper;

    @Inject
    public AccessPointPresenter(
            @NonNull GetAccessPoints getAccessPoints,
            @NonNull AccessPointViewModelToAccessPointMapper mapper
    ){
        this.getAccessPoints = getAccessPoints;
        this.mapper = mapper;
    }

    @Override
    public void initialize()
    {
        super.initialize();
        getView().showLoading();
        getAccessPoints.execute(new AccessPointListObserver());
    }

    public void onAccessPointClicked(AccessPointViewModel accessPointViewModel)
    {
        //TODO: implementar la vista
    }

    public void destroy()
    {
        this.getAccessPoints.dispose();
        setView(null);
    }


    public interface View extends Presenter.View
    {
        void showAccessPoint(List<AccessPointViewModel> accessPointViewModelList);
        void openAccessPointScreen(AccessPointViewModel accessPointViewModel);
    }

    private final class AccessPointListObserver extends UseCaseObserver<List<AccessPoint>>
    {
        @Override
        public void onComplete()
        {
            getView().hideLoading();
        }

        @Override
        public void onError(Throwable e)
        {
            getView().hideLoading();
            e.printStackTrace();
        }

        public void onNext(List<AccessPoint> accessPointList)
        {
            List<AccessPointViewModel>  accessPointViewModels = mapper.reverseMap(accessPointList);
            getView().showAccessPoint(accessPointViewModels);
        }
    }
}
