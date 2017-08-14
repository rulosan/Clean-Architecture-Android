package com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.mapper;

import android.util.Log;

import com.example.jhordan.euro_cleanarchitecture.data.entity.AccessPointEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by rulosan on 8/14/17.
 */

public class AccessPointEntityJsonMapper {
    private final Gson gson;

    @Inject
    public AccessPointEntityJsonMapper(){
        this.gson = new Gson();
    }


    public AccessPointEntity transformAccesPointEntity(String jsonResponse) throws JsonSyntaxException
    {
        AccessPointEntity accessPointEntity;
        try
        {
            Type typeAccessPointEnty = new TypeToken<AccessPointEntity>(){}.getType();
            accessPointEntity = this.gson.fromJson(jsonResponse, typeAccessPointEnty);
            return accessPointEntity;
        }
        catch(JsonSyntaxException exception)
        {
            Log.d("JSON_SYNTAX", exception.getMessage());
            throw exception;
        }
    }

    public List<AccessPointEntity> transformAccessPointCollection(String accesPointJsonResponse)
        throws JsonSyntaxException
    {
        List<AccessPointEntity> accessPointEntityList;
        try
        {
            Type typeAccessPointList = new TypeToken<List<AccessPointEntity>>(){
            }.getType();
            accessPointEntityList = this.gson.fromJson(accesPointJsonResponse, typeAccessPointList);
            return accessPointEntityList;
        }
        catch(JsonSyntaxException exception) {
            Log.d("JSON_SYNTAX", exception.getMessage());
            throw exception;
        }
    }
}
