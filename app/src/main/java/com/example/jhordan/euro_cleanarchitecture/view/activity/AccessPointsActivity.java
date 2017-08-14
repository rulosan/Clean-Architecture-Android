package com.example.jhordan.euro_cleanarchitecture.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jhordan.euro_cleanarchitecture.EuroApplication;
import com.example.jhordan.euro_cleanarchitecture.R;
import com.example.jhordan.euro_cleanarchitecture.view.adapter.AccessPointsAdapter;
import com.example.jhordan.euro_cleanarchitecture.view.base.view.BaseActivity;
import com.example.jhordan.euro_cleanarchitecture.view.presenter.AccessPointPresenter;
import com.example.jhordan.euro_cleanarchitecture.view.viewmodel.AccessPointViewModel;
import com.example.jhordan.euro_cleanarchitecture.view.widget.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointsActivity extends BaseActivity implements AccessPointPresenter.View
{
    @Inject
    AccessPointPresenter presenter;

    @BindView(R.id.list_accesspoint)
    RecyclerView accessPointList;


    @BindView(R.id.progress_accesspoint)
    ProgressBar accessPointProgress;

    private AccessPointsAdapter adapter;

    @Override
    public void initView() {
        super.initView();
        initializeDagger();
        initializePresenter();
        disableTitleFromToolbar();
        initializeAdapter();
        initializeRecyclerView();
        presenter.initialize();
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_accesspoints;
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_code) {
            final String repositoryURL = "https://github.com/erikcaffrey/Clean-Architecture-Android";
            startActivityActionView(repositoryURL);
        } else {
            String blogURL = "https://erikcaffrey.github.io/ANDROID-clean-architecture/";
            startActivityActionView(blogURL);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override public void showLoading() {
        accessPointProgress.setVisibility(View.VISIBLE);
        accessPointList.setVisibility(View.GONE);
    }

    @Override public void hideLoading() {
        accessPointProgress.setVisibility(View.GONE);
        accessPointList.setVisibility(View.VISIBLE);
    }

    private void initializeAdapter() {
        adapter = new AccessPointsAdapter(presenter);
    }

    private void initializeRecyclerView() {
        accessPointList.setLayoutManager(new LinearLayoutManager(this));
        accessPointList.addItemDecoration(
                new DividerItemDecoration(AccessPointsActivity.this, DividerItemDecoration.VERTICAL_LIST));
        accessPointList.setHasFixedSize(true);
        accessPointList.setAdapter(adapter);
    }

    private void initializeDagger() {
        EuroApplication app = (EuroApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
    }

    private void disableTitleFromToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void startActivityActionView(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }


    @Override
    public void showAccessPoint(List<AccessPointViewModel> accessPointViewModelList) {
        adapter.addAll(accessPointViewModelList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openAccessPointScreen(AccessPointViewModel accessPointViewModel) {

    }
}
