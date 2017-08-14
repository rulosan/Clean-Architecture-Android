package com.example.jhordan.euro_cleanarchitecture.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;
import com.example.jhordan.euro_cleanarchitecture.data.entity.TeamEntity;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper.AccessPointEntityJsonMapper;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper.TeamEntityJsonMapper;
import com.example.jhordan.euro_cleanarchitecture.domain.model.AccessPoint;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointWebImp implements ApiInterface {

    private final Context context;
    private final AccessPointEntityJsonMapper accessPointEntityJsonMapper;

    public AccessPointWebImp(
            @NonNull Context context,
            @NonNull AccessPointEntityJsonMapper mapper
    )
    {
        this.context = context;
        this.accessPointEntityJsonMapper = mapper;
    }

    @Override
    public Observable<List<TeamEntity>> teamEntityList() {
        return null;
    }

    @Override
    public Observable<TeamEntity> teamEntity(String flag) {
        return null;
    }

    @Override
    public Observable<List<AccessPointEntity>> accessPointList() {
        return Observable.create(new ObservableOnSubscribe<List<AccessPointEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AccessPointEntity>> e) throws Exception {
                List<AccessPointEntity> accessPointList = getRemoteAccessPointList();
                if(accessPointList != null)
                {
                    e.onNext(accessPointList);
                    e.onComplete();
                }
                else
                {
                    e.onError(
                            new Throwable("Error en la consulta de access point list")
                    );
                }
            }
        });
    }

    @Override
    public Observable<AccessPointEntity> accessPoint(final int searchedId) {
        return Observable.create(new ObservableOnSubscribe<AccessPointEntity>() {
            @Override
            public void subscribe(ObservableEmitter<AccessPointEntity> e) throws Exception {
                AccessPointEntity entity = getEntityById(searchedId);
                if(entity != null)
                {
                    e.onNext(entity);
                    e.onComplete();
                }
                else
                {
                    e.onError(new Throwable("Error en la consulta de Access Point por Id"));
                }
            }
        });
    }

    private AccessPointEntity getEntityById(int id)
    {
        AccessPointEntity result = null;
        List<AccessPointEntity> accessPointList = getRemoteAccessPointList();
        for(AccessPointEntity entity : accessPointList)
        {
            if(entity.getId() == id)
            {
                result = entity;
                break;
            }
        }
        return result;
    }

    private List<AccessPointEntity> getRemoteAccessPointList()
    {
        String json = null;
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder()
                .url("https://asynchoke.me/accesspoint.php")
                .addHeader("User-Agent", "Curso-de-Android-Clean-Architecture")
                .get()
                .build();
        try
        {
            Response resp = client.newCall(req).execute();
            json = resp.body().string();
        }
        catch (IOException ex)
        {
            Log.e("WEB_REQUEST", ex.getMessage());
        }
        if (json != null)
            return accessPointEntityJsonMapper.transformAccessPointCollection(json);
        return null;

    }
}
