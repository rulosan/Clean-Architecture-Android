package com.example.jhordan.euro_cleanarchitecture.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhordan.euro_cleanarchitecture.R;
import com.example.jhordan.euro_cleanarchitecture.view.presenter.AccessPointPresenter;
import com.example.jhordan.euro_cleanarchitecture.view.viewmodel.AccessPointViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private AccessPointPresenter presenter;
    private final List<AccessPointViewModel> accessPointViewModelList;

    public AccessPointsAdapter(@NonNull AccessPointPresenter presenter)
    {
        this.presenter = presenter;
        this.accessPointViewModelList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accesspoint_row, parent, false);
        return new AccessPointViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AccessPointViewHolder accessPointViewHolder = (AccessPointViewHolder) holder;
        AccessPointViewModel model = this.accessPointViewModelList.get(position);
        accessPointViewHolder.render(model);
    }

    @Override
    public int getItemCount() {
        return this.accessPointViewModelList.size();
    }

    public void addAll(Collection<AccessPointViewModel> collection)
    {
        accessPointViewModelList.addAll(collection);
    }
}
