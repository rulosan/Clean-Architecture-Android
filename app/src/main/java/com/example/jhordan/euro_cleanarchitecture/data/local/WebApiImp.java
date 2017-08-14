package com.example.jhordan.euro_cleanarchitecture.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.jhordan.euro_cleanarchitecture.data.entity.TeamEntity;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper.TeamEntityJsonMapper;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rulosan on 8/12/17.
 */

public class WebApiImp implements ApiInterface
{

    private final Context context;
    private final TeamEntityJsonMapper teamEntityJsonMapper;

    public WebApiImp(@NonNull Context context,
                     @NonNull TeamEntityJsonMapper teamEntityJsonMapper)
    {
        this.context = context;
        this.teamEntityJsonMapper = teamEntityJsonMapper;
    }

    @Override
    public Observable<List<TeamEntity>> teamEntityList() {
        return Observable.create(new ObservableOnSubscribe<List<TeamEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<TeamEntity>> emitter) throws Exception {
                List<TeamEntity> teamList = getResponseFromRemoteJson();
                if (teamList != null){
                    emitter.onNext(teamList);
                    emitter.onComplete();
                }
                else{
                    emitter.onError(
                            new Throwable("Error al traer del servicio web los equipos")
                    );
                }
            }
        });
    }

    @Override
    public Observable<TeamEntity> teamEntity(final String seachedFlag) {

        return Observable.create(new ObservableOnSubscribe<TeamEntity>() {
            @Override
            public void subscribe(ObservableEmitter<TeamEntity> emitter) throws Exception {
                TeamEntity entity = searchByFlag(seachedFlag);
                if(entity != null)
                {
                    emitter.onNext(entity);
                    emitter.onComplete();
                }
                else
                {
                    emitter.onError(
                            new Throwable("Error al obtener un equipo por bandera"));
                }
            }
        });
    }

    private TeamEntity searchByFlag(String flag){
        TeamEntity result = null;
        List<TeamEntity> entityList = this.getResponseFromRemoteJson();
        if(entityList != null) {
            for (TeamEntity entity : entityList) {
                if (entity.getTeamFlag().equals(flag)) {
                    result = entity;
                    break;
                }
            }
        }
        return result;
    }

    private List<TeamEntity> getResponseFromRemoteJson(){
        String json = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://asynchoke.me/euro_data.php")
                .addHeader("User-Agent","Curso-de-Android-Clean-Architecture")
                .get()
                .build();
        try
        {
            Response response = client.newCall(request).execute();
            json = response.body().string();
        }
        catch (IOException ex)
        {
            Log.e("WEB_REQUEST", ex.getMessage());
        }
        if (json != null)
            return teamEntityJsonMapper.transformTeamEntityCollection(json);
        return null;
    }
}
