package com.example.jhordan.euro_cleanarchitecture.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jhordan.euro_cleanarchitecture.R;
import com.example.jhordan.euro_cleanarchitecture.view.presenter.AccessPointPresenter;
import com.example.jhordan.euro_cleanarchitecture.view.viewmodel.AccessPointViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointViewHolder extends RecyclerView.ViewHolder {


    private final AccessPointPresenter presenter;

    @BindView(R.id.ssid_name)
    TextView ssidName;
    @BindView(R.id.macaddress)
    TextView maccaddress;
    @BindView(R.id.identificador)
    TextView identificador;

    public AccessPointViewHolder(
            @NonNull View itemView,
            @NonNull AccessPointPresenter accessPointPresenter
            ) {
        super(itemView);
        this.presenter = accessPointPresenter;
        ButterKnife.bind(this, itemView);
    }

    public void render(AccessPointViewModel accessPointViewModel)
    {
        this.ssidName.setText(accessPointViewModel.getSsid());
        this.maccaddress.setText(accessPointViewModel.getMacaddress());
        this.identificador.setText(String.valueOf(accessPointViewModel.getId()));
    }

    private Context getContext()
    {
        return itemView.getContext();
    }
}
